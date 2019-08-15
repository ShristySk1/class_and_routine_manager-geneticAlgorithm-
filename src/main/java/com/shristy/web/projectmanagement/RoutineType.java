/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public abstract class RoutineType {
    public abstract void readInput() throws IOException,SQLException;
}
