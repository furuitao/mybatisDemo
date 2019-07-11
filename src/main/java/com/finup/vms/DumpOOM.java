package com.finup.vms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by furuitao on 2019/2/21.
 */
public class DumpOOM {
    public static void main(String[] args){
        List<DumpOOM> list = new ArrayList<DumpOOM>();
        while (true){
            list.add(new DumpOOM());
        }
    }
}
