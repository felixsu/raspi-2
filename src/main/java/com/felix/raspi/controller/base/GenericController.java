package com.felix.raspi.controller.base;

import com.felix.raspi.service.base.GenericService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * Created by fsoewito on 2/25/2016.
 */
public class GenericController<T extends Object, ID extends Serializable> {
    private GenericService<T, ID> service;

    public GenericController(GenericService<T, ID> service) {
        this.service = service;
    }

    @RequestMapping(
            method = RequestMethod.POST)
    @ResponseBody
    public T save(@RequestBody T instance) {
        return service.create(instance);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public T update(@RequestBody T instance, @PathVariable ID id) {
        return service.update(instance, id);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable ID id) {
        service.delete(id);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public T findOne(@PathVariable ID id) {
        return service.findOne(id);
    }
}
