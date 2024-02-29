/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.ui.editors;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.event.DocumentListener;

import com.mirth.connect.client.ui.components.MirthFieldConstraints;

public class FrameModeSettingsPanel extends javax.swing.JPanel {

    private JComponent fillerComponent;

    /**
     * Creates new form FrameEncodingSettingsPanel
     */
    public FrameModeSettingsPanel(DocumentListener documentListener) {
        initComponents();
        startOfMessageBytesField.setDocument(new MirthFieldConstraints(0, true, true, true));
        endOfMessageBytesField.setDocument(new MirthFieldConstraints(0, true, true, true));
        startOfMessageBytesField.getDocument().addDocumentListener(documentListener);
        endOfMessageBytesField.getDocument().addDocumentListener(documentListener);
        fillerComponent = fillerLabel;
    }

    public void switchComponent(JComponent component) {
        ((GroupLayout) getLayout()).replace(fillerComponent, component);
        fillerComponent = component;
    }

    // @formatter:off
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startOfMessageBytes0XLabel = new javax.swing.JLabel();
        endOfMessageBytes0XLabel = new javax.swing.JLabel();
        endOfMessageBytesField = new com.mirth.connect.client.ui.components.MirthTextField();
        startOfMessageBytesField = new com.mirth.connect.client.ui.components.MirthTextField();
        messageDataLabel = new javax.swing.JLabel();
        fillerLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        startOfMessageBytes0XLabel.setText("0x");
        startOfMessageBytes0XLabel.setToolTipText("<html>Enter the bytes to send before the beginning and after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.<br/><br/><b>Sample Frame: SOM <i>&lt;Message Data&gt;</i> EOM</b></html>");

        endOfMessageBytes0XLabel.setText("0x");
        endOfMessageBytes0XLabel.setToolTipText("<html>Enter the bytes to send before the beginning and after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.<br/><br/><b>Sample Frame: SOM <i>&lt;Message Data&gt;</i> EOM</b></html>");

        endOfMessageBytesField.setToolTipText("<html>Enter the bytes to send before the beginning and after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.<br/><br/><b>Sample Frame: SOM <i>&lt;Message Data&gt;</i> EOM</b></html>");
        endOfMessageBytesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endOfMessageBytesFieldActionPerformed(evt);
            }
        });

        startOfMessageBytesField.setToolTipText("<html>Enter the bytes to send before the beginning and after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.<br/><br/><b>Sample Frame: SOM <i>&lt;Message Data&gt;</i> EOM</b></html>");
        startOfMessageBytesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startOfMessageBytesFieldActionPerformed(evt);
            }
        });

        messageDataLabel.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        messageDataLabel.setText("<Message Data>");
        messageDataLabel.setToolTipText("<html>Enter the bytes to send before the beginning and after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.<br/><br/><b>Sample Frame: SOM <i>&lt;Message Data&gt;</i> EOM</b></html>");
        messageDataLabel.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(startOfMessageBytes0XLabel)
                .addGap(3, 3, 3)
                .addComponent(startOfMessageBytesField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(messageDataLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endOfMessageBytes0XLabel)
                .addGap(3, 3, 3)
                .addComponent(endOfMessageBytesField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fillerLabel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(startOfMessageBytes0XLabel)
                .addComponent(startOfMessageBytesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(messageDataLabel)
                .addComponent(endOfMessageBytes0XLabel)
                .addComponent(endOfMessageBytesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(fillerLabel))
        );
    }// </editor-fold>//GEN-END:initComponents
    // @formatter:on

    private void endOfMessageBytesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endOfMessageBytesFieldActionPerformed

    }//GEN-LAST:event_endOfMessageBytesFieldActionPerformed

    private void startOfMessageBytesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startOfMessageBytesFieldActionPerformed

    }//GEN-LAST:event_startOfMessageBytesFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel endOfMessageBytes0XLabel;
    public com.mirth.connect.client.ui.components.MirthTextField endOfMessageBytesField;
    private javax.swing.JLabel fillerLabel;
    public javax.swing.JLabel messageDataLabel;
    public javax.swing.JLabel startOfMessageBytes0XLabel;
    public com.mirth.connect.client.ui.components.MirthTextField startOfMessageBytesField;
    // End of variables declaration//GEN-END:variables
}
