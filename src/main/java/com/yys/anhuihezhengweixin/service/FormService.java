package com.yys.anhuihezhengweixin.service;

import com.yys.anhuihezhengweixin.dao.*;
import com.yys.anhuihezhengweixin.entity.base.*;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.yys.anhuihezhengweixin.util.HtmlTag.SIZE;

@Service
public class FormService {

    private final JobDao jobDao;
    private final RecordDao recordDao;
    private final PayDao payDao;
    private final FormDao formDao;
    private final BackTypeDao backTypeDao;
    private final BackFormEntityDao backFormEntityDao;

    @Autowired
    public FormService(JobDao jobDao, RecordDao recordDao, PayDao payDao, FormDao formDao, BackTypeDao backTypeDao, BackFormEntityDao backFormEntityDao) {
        this.jobDao = jobDao;
        this.recordDao = recordDao;
        this.payDao = payDao;
        this.formDao = formDao;
        this.backTypeDao = backTypeDao;
        this.backFormEntityDao = backFormEntityDao;
    }

    public List<Job> getJobs(){
        Sort sort = Sort.by(Sort.Direction.ASC,"jobId");
        return jobDao.findAll(sort);
    }

    public List<Record> getRecords(){
        Sort sort = Sort.by(Sort.Direction.ASC,"recordId");
        return recordDao.findAll(sort);
    }

    public List<Pay> getPays(){
        Sort sort = Sort.by(Sort.Direction.ASC,"payId");
        return payDao.findAll(sort);
    }


    public List<BackType> getBackTypes(){
        Sort sort = Sort.by(Sort.Direction.ASC,"backId");
        return backTypeDao.findAll(sort);
    }

    public FormEntity saveForm(FormEntity formEntity){
        return formDao.save(formEntity);
    }

    public BackFormEntity saveFormBack(BackFormEntity backFormEntity){

        return backFormEntityDao.save(backFormEntity);
    }

    public Boolean deleteJobByJobId(Integer jobId){
        List<Job> allByJobId = jobDao.findAllByJobId(jobId);
        if(allByJobId == null || allByJobId.size()<1){
            return false;
        }
        Job job = allByJobId.get(0);

        if(job == null){
            return false;
        }
        jobDao.delete(job);
        return true;
    }

    public Job getJobByJobId(Integer id){
        List<Job> allByJobId = jobDao.findAllByJobId(id);
        if (allByJobId == null || allByJobId.size()<1) {
            return null;
        }
        Job job = allByJobId.get(0);
        if(job == null){
            return null;
        }
        return job;

    }

    public Job saveJob(Job jobByJobId) {
        return jobDao.save(jobByJobId);
    }

    public Page<FormEntity> getFormList(Integer page){

        Pageable pageable = PageRequest.of(page - 1, SIZE);
        return formDao.findAll(pageable);
    }

    public void deletePerson(FormEntity formEntity) {
        formDao.delete(formEntity);
    }

    public FormEntity findPersonById(Long id){
        Optional<FormEntity> byId = formDao.findById(id);
        return byId.orElse(null);
    }

    public Page<BackFormEntity> getBackFormList(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, SIZE);
        return backFormEntityDao.findAll(pageable);
    }

    public BackFormEntity findBackInfo(Long id) {
        Optional<BackFormEntity> byId = backFormEntityDao.findById(id);
        return byId.orElse(null);
    }
    public void deleteBackForm(BackFormEntity backFormEntity) {
        backFormEntityDao.delete(backFormEntity);
    }

    public Long getPayCount() {
        return payDao.count();
    }

    public Pay savePay(Pay pay) {
        return payDao.save(pay);
    }

    public Long getRecordCount() {
        return recordDao.count();
    }

    public Record saveRecord(Record record) {
        return recordDao.save(record);
    }

    public void deletePay(Pay payById) {
        payDao.delete(payById);
    }

    public void deleteRecord(Record recordById) {
        recordDao.delete(recordById);
    }

    public Pay findPayByPayId(Integer payId) {
        List<Pay> allByPayId = payDao.findAllByPayId(payId);
        if(allByPayId == null || allByPayId.size() < 1){
            return null;
        } else {
            return allByPayId.get(0);
        }
    }

    public Record findRecordByRecordId(Integer recordId) {
        List<Record> allByRecordId = recordDao.findAllByRecordId(recordId);
        if(allByRecordId == null || allByRecordId.size() < 1){
            return null;
        } else {
            return allByRecordId.get(0);
        }
    }
}
