package com.globallogic.dashboard.fte;

import org.springframework.stereotype.Service;


public interface FteFacade {

    FteResponseDto createFte(FteCreateDto fteCreateDto);

}
