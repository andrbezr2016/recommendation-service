package com.andrbezr2016.library.recommendation.job;

import com.andrbezr2016.library.recommendation.service.CatalogLoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CatalogLoaderJob {

    private final CatalogLoaderService catalogLoaderService;
}
