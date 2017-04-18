/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import com.persistence.NoticeMap;
import java.util.ArrayList;

/**
 *
 * @author kinya
 */
public class NoticeArray extends ArrayList<NoticeMap> {

    @Override
    public boolean add(NoticeMap e) {
        e.id = this.size();
        return super.add(e);
    }
    
}
