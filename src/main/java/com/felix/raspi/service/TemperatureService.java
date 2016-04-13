package com.felix.raspi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by fsoewito on 3/31/2016.
 */

@Service
public class TemperatureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureService.class.getName());

    private static final String ROOT = "/sys/bus/w1/devices";

    @Resource(name = "temperatureValueMap")
    public ConcurrentMap<String, Integer> temperatureValueMap;

    @Scheduled(cron = "${bp.temperatureScan}")
    public void scanTemperature() {
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
                                String tempString = currentLine.substring(29,34);
                                Integer temp = Integer.valueOf(tempString);
                                temperatureValueMap.put(dirName, temp);
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

    private class SubDirFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir, name).isDirectory();
        }
    }
}
