package com.young.extensiontype.service;

import com.young.extensiontype.entity.Extension;
import com.young.extensiontype.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;



import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
@Log4j2
@Service
public class ExtensionServiceImpl implements ExtensionService{

    private final ExtensionRepository repository;
    @Override
    public void addExtension(String ext) {
        Iterable<Extension> result=repository.findAll();
        for (Object a : result) {
            if(((Extension)a).getExt().equals(ext))
                return;
        }
        Extension extension=Extension.builder().ext(ext).build();
        repository.save(extension);
    }

    @Override
    public void deleteExtension(String ext) {
        Iterable<Extension> result=repository.findAll();
        for (Object a : result) {
            if(((Extension)a).getExt().equals(ext)){
                repository.deleteById(((Extension)a).getExt());
                return;
            }
        }
    }

    @Override
    public List<Extension> getCurrentExtension() {

        List<Extension> extList=new ArrayList<>();
        Iterable<Extension> result=repository.findAll();
        result.forEach(extList::add);
        for(Extension e:extList)
            System.out.print("extension:"+e+" ");
        return extList;
    }

    @Override
    public List<String> getInitialExtension() {
        String[] extL={"bat","cmd","com","cpl","exe","scr","js"};
        List<Extension> extList=new ArrayList<>();
        List<String> sList=new ArrayList<>();
        Iterable<Extension> result=repository.findAll();
        result.forEach(extList::add);
        for(Extension e:extList){
            sList.add(e.getExt());
        }
        for(String s: extL)
            sList.remove(s);


        return sList;
    }
}
