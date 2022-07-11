package com.bbirincioglu.amazon.store.utilities.controller;

import com.bbirincioglu.amazon.store.utilities.model.ProductInfo;
import com.bbirincioglu.amazon.store.utilities.service.AmazonClient;
import com.bbirincioglu.amazon.store.utilities.service.AsuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/asu")
public class AsuController {
    @Autowired
    private AsuService asuService;

    @Autowired
    private AmazonClient amazonClient;

    @GetMapping("/getHelperPage")
    public ModelAndView getHelperPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productInfo", new ProductInfo());
        modelAndView.setViewName("helperPage.html");
        return modelAndView;
    }

    @PostMapping("/submitForm")
    public ModelAndView submitForm(@ModelAttribute ProductInfo productInfo) {
        ModelAndView modelAndView = new ModelAndView();
        productInfo.setWeight(productInfo.getWeight() * 2);
        modelAndView.addObject("productInfo", productInfo);
        modelAndView.setViewName("helperPage.html");
        System.out.println(amazonClient.getAmazonSpecificInfo(productInfo.getAsinNumber(), productInfo.getAmazonPrice(), productInfo.getCount(), productInfo.getMonthlySoldCount(), productInfo.isOctoberDecember()));
        return modelAndView;
    }
}
