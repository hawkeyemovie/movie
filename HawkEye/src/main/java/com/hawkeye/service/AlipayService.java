package com.hawkeye.service;

import com.hawkeye.pojo.MovieTicket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AlipayService {
    void aliPay(HttpServletResponse response, HttpServletRequest request, MovieTicket movieTicket);
}
