package com.felix.raspi.service;

import com.felix.raspi.exception.ResourceNotFoundException;
import com.felix.raspi.exception.SystemNotReadyException;
import com.felix.raspi.model.Temperature;
import com.felix.raspi.model.entity.TemperatureDto;
import com.felix.raspi.repository.TemperatureRepository;
import com.felix.raspi.service.base.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fsoewito on 3/31/2016.
 */

@Service
public class TemperatureService  extends GenericService<TemperatureDto, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureService.class.getName());

    private static final String ROOT = "/sys/bus/w1/devices";

    @Resource(name = "temperatureHolder")
    private ConcurrentMap<String, Temperature> temperatureHolder;

    private TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureRepository repository) {
        super(repository);
        this.temperatureRepository = repository;
    }

    @Scheduled(cron = "${bp.temperatureScan}")
    public void scanIndoorTemperature() {
        File f = new File(ROOT);
        String[] directories = f.list(new SubDirFilter());

        BufferedReader br = null;
        try {
            if (directories.length > 0) {
                for (String dirName : directories) {
                    LOGGER.debug("dir name " + dirName);
                    if (dirName.substring(0, 2).equals("28")) {
                        File temperatureFile = new File(ROOT + "/" + dirName + "/" + "w1_slave");
                        FileReader fileReader = new FileReader(temperatureFile);
                        br = new BufferedReader(fileReader);

                        String currentLine;
                        int i = 0;
                        while ((currentLine = br.readLine()) != null) {
                            if (i == 1){
                                putTemperature(currentLine, dirName);
                            }
                            i++;
                        }

                        if (br != null){
                            br.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }

            }
        }
    }

    public Temperature readTemperature(String id) throws ResourceNotFoundException, SystemNotReadyException {
        if (temperatureHolder.isEmpty()){
            throw new SystemNotReadyException();
        }else {
            if (!temperatureHolder.containsKey(id)){
                throw new ResourceNotFoundException();
            } else{
                return temperatureHolder.get(id);
            }
        }
    }

    public Temperature[] readAll() throws SystemNotReadyException{
        if (temperatureHolder.isEmpty()){
            throw new SystemNotReadyException();
        }else {
            Temperature[] result = new Temperature[temperatureHolder.size()];
            temperatureHolder.entrySet().forEach(ConcurrentMap.Entry<String, Temperature>::getValue);
            return result;

        }
    }

    public String[] readBean(){
        if (temperatureHolder.isEmpty()){
            throw new SystemNotReadyException();
        } else {
            Set<String> keySet = temperatureHolder.keySet();
            String[] result = new String[keySet.size()];
            int i = 0;
            for (String element : keySet){
                result[i] = element;
                i++;
            }
            return result;
        }
    }

    private void putTemperature(String temperatureString, String deviceId){
        String tempString = temperatureString.substring(29,34);
        Integer temp = Integer.valueOf(tempString);

        Temperature t;
        if (temperatureHolder.containsKey(deviceId)){
            t = temperatureHolder.get(deviceId);

            int currTemp = temp /100;
            Double dCurrTemp= ((double)currTemp)/10;

            t.setTemperature(dCurrTemp);
            t.setApparentTemperature(dCurrTemp);
        } else {
            //todo get device name using from db
            t = new Temperature(deviceId, temp, temp, "indoor1");
            temperatureHolder.put(deviceId, t);
        }

    }

    @Scheduled(cron = "${bp.persistTemperature}")
    public void persistTemperature(){
        if (!temperatureHolder.isEmpty()) {
            for (Map.Entry<String, Temperature> entry : temperatureHolder.entrySet()) {
                Temperature value = entry.getValue();

                TemperatureDto dto = new TemperatureDto(value);
                repository.save(dto);
            }
        }
    }

    private class SubDirFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir, name).isDirectory();
        }
    }
}
