package com.tcrj.micro;

import java.util.List;

/**
 * Created by leict on 2019/1/23.
 */

public interface PermissionListener {
    void granted();
    void denied(List<String> deniedList);
}

