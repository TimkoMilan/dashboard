package com.globallogic.dashboard.fte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fte")
public class FteResource {

    @Autowired
    FteFacade fteFacade;


    @PostMapping("createFte")
    public ResponseEntity<FteResponseDto> createFte (@RequestBody FteCreateDto fteCreateDto){
        return ResponseEntity.ok(fteFacade.createFte(fteCreateDto));
    }


}
