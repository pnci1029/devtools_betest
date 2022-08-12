package com.example.demo.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
@Component
@Getter
public class WhatTimeIsIt {

    private final SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 d일 HH시 m분 s초"); // 체크

    private final Calendar nowDate = Calendar.getInstance();

    private final String now = date.format(nowDate.getTime());

}