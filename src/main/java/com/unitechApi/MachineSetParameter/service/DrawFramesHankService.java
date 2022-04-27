package com.unitechApi.MachineSetParameter.service;

import com.unitechApi.MachineSetParameter.model.DrawFramesPerHank;
import com.unitechApi.MachineSetParameter.repository.DrawFramesPerHankRepository;
import com.unitechApi.Payload.response.Pagination;
import com.unitechApi.exception.ExceptionService.DateMisMatchException;
import com.unitechApi.exception.ExceptionService.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;

@Slf4j
@Service
public class DrawFramesHankService {
    public static final float CONSTANT = (float) 0.6251;

    private final DrawFramesPerHankRepository drawFramesPerHankRepository;
    DecimalFormat df = new DecimalFormat("#.###");

    public DrawFramesHankService(DrawFramesPerHankRepository drawFramesPerHankRepository) {
        this.drawFramesPerHankRepository = drawFramesPerHankRepository;
    }

    public DrawFramesPerHank save(DrawFramesPerHank drawFramesPerHank) {
        df.setMaximumFractionDigits(3);
        drawFramesPerHank.setProductiononratekgdfper8hour(Float.parseFloat(df.format(CONSTANT * drawFramesPerHank.getDeliveryspeed() * drawFramesPerHank.getMachineefficency() / 100)));
        drawFramesPerHank.setMachineefficencykgdfper6hours(Float.parseFloat(df.format((drawFramesPerHank.getProductiononratekgdfper8hour() * 6) / 8)));
        drawFramesPerHank.setMachineefficencykgdfperday(Float.parseFloat(df.format((drawFramesPerHank.getProductiononratekgdfper8hour() * 24) / 8)));
        log.info(" { }DrawFrames In Hank Data ", drawFramesPerHank);
        return drawFramesPerHankRepository.save(drawFramesPerHank);
    }

    public DrawFramesPerHank FindByData(Long id) {

        return drawFramesPerHankRepository.findById(id).orElseThrow(() -> new ResourceNotFound("can't find data"));
    }

    public Page<DrawFramesPerHank> FindByDate(Date start, Date end, Pagination pagination) {
        java.util.Date date = new java.util.Date();

        if (date.before(start)) {
            throw new DateMisMatchException(" you can not enter -> " + date + "  -> " + start);
        } else if (date.before(end)) {
            throw new DateMisMatchException(" you can not enter -> " + date + "  -> " + end);
        }
        return drawFramesPerHankRepository.findByCreatedAtBetween(start, end, pagination.getpageble());
    }

    public Page<DrawFramesPerHank> FindBySingleDate(Date start, Pagination pagination) {
        return drawFramesPerHankRepository.findByCreatedAt(start, pagination.getpageble());
    }

    public List<DrawFramesPerHank> ExcelDateToPerDateReport(Date start) {
        return drawFramesPerHankRepository.findByShiftdate(start);
    }

    public List<DrawFramesPerHank> ExcelDateToDateReport(Date start, Date end) {
        return drawFramesPerHankRepository.findByShiftdateBetween(start, end);
    }
}
