package pl.sytomczak.fokusmanager.calender;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;

public class CalenderView extends JFrame {

    Frame frame = new Frame();

    UtilDateModel utilDateModel = new UtilDateModel();
    JDatePanelImpl datePanel = new JDatePanelImpl(utilDateModel);
    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
    
}
