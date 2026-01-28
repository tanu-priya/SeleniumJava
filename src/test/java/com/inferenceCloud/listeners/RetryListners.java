package com.inferenceCloud.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;

public class RetryListners implements IAnnotationTransformer{
    @Override
    public void transform(org.testng.annotations.ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(com.inferenceCloud.retry.RetryAnalyzer.class);
    }
    
}

