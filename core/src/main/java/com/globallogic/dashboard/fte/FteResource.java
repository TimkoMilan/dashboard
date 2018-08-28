package com.globallogic.dashboard.fte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fte")
public class FteResource {

    @Autowired
    FteFacade fteFacade;

    //The counting of months starts from 0
    @PostMapping("createFte")
    public ResponseEntity<FteResponseDto> createFte (@RequestBody FteCreateDto fteCreateDto){
        return ResponseEntity.ok(fteFacade.createFte(fteCreateDto));
    }

}
