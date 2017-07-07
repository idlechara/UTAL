/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopgui;

import java.io.Serializable;

/**
 *
 * @author kinya
 */
public enum MessageType implements Serializable{
    STUDENT_CHANGE_CLASS, STUDENT_TEST_NOTICE, STUDENT_HELPER, STUDENT_PLANNING, STUDENT_WORK, STUDENT_GENERAL,
    EX_STUDENTS_WORK, EX_STUDENTS_POSTGRADE, EX_STUDENTS_BECA, EX_STUDENTS_GENERAL;
}
