package com.learn.demo.agent;

import java.lang.instrument.Instrumentation;

public class PremainAgent {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("entry");
        inst.addTransformer(new Transformer());
    }
}
