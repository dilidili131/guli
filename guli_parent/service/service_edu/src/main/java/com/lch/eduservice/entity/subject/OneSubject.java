package com.lch.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lch
 * @time: 2022/6/5 21:51
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
