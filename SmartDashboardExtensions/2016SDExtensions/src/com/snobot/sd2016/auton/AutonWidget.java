package com.snobot.sd2016.auton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.snobot.sd2016.config.SmartDashBoardNames;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class AutonWidget extends StaticWidget
{
    public static final String NAME = "2016 Auton Widget";

    private AutonPanel mDefensePanel;
    private AutonPanel mPostDefensePanel;

    private ITable mDefenseTable;
    private ITable mPostDefenseTable;

    public AutonWidget()
    {
        mDefensePanel = new AutonPanel();
        mPostDefensePanel = new AutonPanel();

        setLayout(new BorderLayout());
        add(mDefensePanel, BorderLayout.WEST);
        add(mPostDefensePanel, BorderLayout.EAST);

        mDefenseTable = NetworkTable.getTable(SmartDashBoardNames.sDEFENSE_AUTON_TABLE);
        mPostDefenseTable = NetworkTable.getTable(SmartDashBoardNames.sPOST_DEFENSE_AUTON_TABLE);

        addListener(mDefensePanel, mDefenseTable);
        addListener(mPostDefensePanel, mPostDefenseTable);

        this.setVisible(true);
    }

    private void addListener(AutonPanel aAutonPanel, ITable aAutonTable)
    {
        aAutonPanel.addSaveListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                aAutonTable.putString(SmartDashBoardNames.sROBOT_COMMAND_TEXT, aAutonPanel.getTextArea().getText());
                aAutonTable.putBoolean(SmartDashBoardNames.sSAVE_AUTON, true);
            }
        });

        aAutonPanel.addTextChangedListener(new DocumentListener()
        {
            private void onChange()
            {
                aAutonTable.putBoolean(SmartDashBoardNames.sSAVE_AUTON, false);
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                onChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                onChange();
            }
        });

        ITableListener textUpdatedListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                String auto_text = aAutonTable.getString(SmartDashBoardNames.sROBOT_COMMAND_TEXT, "Nothing Received");
                aAutonTable.putBoolean(SmartDashBoardNames.sSAVE_AUTON, false);
                aAutonPanel.getTextArea().setText(auto_text);

                System.out.println("Getting auto command" + auto_text);

            }
        };

        ITableListener errorListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                boolean parseSuccess = aAutonTable.getBoolean(SmartDashBoardNames.sSUCCESFULLY_PARSED_AUTON, false);
                aAutonPanel.setParseSuccess(parseSuccess);
            }
        };

        ITableListener filenameListener = new ITableListener()
        {
            @Override
            public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3)
            {
                String filename = aAutonTable.getString(SmartDashBoardNames.sAUTON_FILENAME, "NothingReceived.txt");
            }
        };

        aAutonTable.addTableListener(SmartDashBoardNames.sROBOT_COMMAND_TEXT, textUpdatedListener, true);
        aAutonTable.addTableListener(SmartDashBoardNames.sSUCCESFULLY_PARSED_AUTON, errorListener, true);
        aAutonTable.addTableListener(SmartDashBoardNames.sAUTON_FILENAME, filenameListener, true);
    }

    @Override
    public void propertyChanged(Property arg0)
    {
        // Nothing to do
    }

    @Override
    public void init()
    {
        // Nothing to do
    }

}
