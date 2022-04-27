package com.unitechApi.MachineSetParameter.service;

import com.unitechApi.MachineSetParameter.model.Packing;
import com.unitechApi.MachineSetParameter.repository.PackingRepository;
import com.unitechApi.Payload.response.Pagination;
import com.unitechApi.exception.ExceptionService.DateMisMatchException;
import com.unitechApi.exception.ExceptionService.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@Slf4j
public class PackingService {

    private final PackingRepository packingRepository;

    public PackingService(PackingRepository packingRepository) {
        this.packingRepository = packingRepository;
    }

    public Packing SaveData(Packing packing) {
        return packingRepository.save(packing);
    }

    public Object ViewData() {
        return packingRepository.findAll();

    }

    public void DeleteReading(Long id) {
        try {
            packingRepository.deleteById(id);
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound("data already deleted present " + ResourceNotFound.class);
        }

    }

    public Optional<Packing> FindByData(Long id) {
        return Optional.ofNullable(packingRepository.findById(id).orElseThrow(() -> new ResourceNotFound("can't find data")));
    }

    public Page<Packing> FindData(Date start, Date end, Pagination pagination) {
        java.util.Date date = new java.util.Date();

        if (date.before(start)) {
            throw new DateMisMatchException(" you can not enter -> " + date + "  -> " + start);
        } else if (date.before(end)) {
            throw new DateMisMatchException(" you can not enter -> " + date + "  -> " + end);
        }

        return packingRepository.findByCreatedAtBetween(start, end, pagination.getpageble());
    }

    public Page<Packing> FindBySingleDate(Date start, Pagination pagination) {
        return packingRepository.findByCreatedAt(start, pagination.getpageble());
    }

}
