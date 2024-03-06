/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.mirth.connect.client.core.Client;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.client.core.ConnectServiceUtil;
import com.mirth.connect.client.core.UnauthorizedException;
import com.mirth.connect.client.core.api.servlets.UserServletInterface;
import com.mirth.connect.client.ui.util.DisplayUtil;
import com.mirth.connect.model.ExtendedLoginStatus;
import com.mirth.connect.model.LoginStatus;
import com.mirth.connect.model.PublicServerSettings;
import com.mirth.connect.model.User;
import com.mirth.connect.model.converters.ObjectXMLSerializer;
import com.mirth.connect.plugins.MultiFactorAuthenticationClientPlugin;
import com.mirth.connect.util.MirthSSLUtil;

public class LoginPanel extends javax.swing.JFrame {

    private Client client;
    private static final String ERROR_MESSAGE = "There was an error connecting to the server at the specified address. Please verify that the server is up and running.";
    private static LoginPanel instance = null;

    private LoginPanel() {
        initComponents();
        DisplayUtil.setResizable(this, false);
        jLabel2.setForeground(UIConstants.HEADER_TITLE_TEXT_COLOR);
        jLabel5.setForeground(UIConstants.HEADER_TITLE_TEXT_COLOR);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(UIConstants.MIRTH_FAVICON.getImage());
        ImageIcon imageIcon = UIConstants.MIRTHCORP_LOGO; // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(175, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        imageIcon = new ImageIcon(newimg);
        
        mirthCorpImage.setIcon(imageIcon);
        mirthCorpImage.setText("");
        mirthCorpImage.setToolTipText(UIConstants.MIRTHCORP_TOOLTIP);
        mirthCorpImage.setCursor(new Cursor(Cursor.HAND_CURSOR));

        mirthCorpImage.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BareBonesBrowserLaunch.openURL(UIConstants.MIRTHCORP_URL);
            }
        });

        mirthCorpImage1.setIcon(imageIcon);
        mirthCorpImage1.setText("");
        mirthCorpImage1.setToolTipText(UIConstants.MIRTHCORP_TOOLTIP);
        mirthCorpImage1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        mirthCorpImage1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BareBonesBrowserLaunch.openURL(UIConstants.MIRTHCORP_URL);
            }
        });

        placeholderButton.setVisible(false);

        errorTextArea.setBackground(Color.WHITE);
        errorTextArea.setDisabledTextColor(Color.RED);
    }

    public static LoginPanel getInstance() {
        synchronized (LoginPanel.class) {
            if (instance == null) {
                instance = new LoginPanel();
            }
            return instance;
        }
    }

    public void initialize(String mirthServer, String version, String user, String pass) {
        synchronized (this) {
            // Do not initialize another login window if one is already visible
            if (isVisible()) {
                return;
            }

            PlatformUI.CLIENT_VERSION = version;

            setTitle("Mirth Connect " + version + " - Login");
            setIconImage(UIConstants.MIRTH_FAVICON.getImage());

            serverName.setText(mirthServer);

            // Make sure the login window is centered and not minimized
            setLocationRelativeTo(null);
            setState(Frame.NORMAL);

            errorPane.setVisible(false);
            loggingIn.setVisible(false);
            loginMain.setVisible(true);
            loginProgress.setIndeterminate(false);

            setStatus("Logging in...");

            username.setText(user);
            password.setText(pass);

            username.grabFocus();

            setVisible(true);
        }

        if (user.length() > 0 && pass.length() > 0) {
            loginButtonActionPerformed(null);
        }
    }

    // @formatter:off
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginMain = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        serverName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mirthHeadingPanel2 = new com.mirth.connect.client.ui.MirthHeadingPanel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        mirthCorpImage = new javax.swing.JLabel();
        errorPane = new javax.swing.JScrollPane();
        errorTextArea = new javax.swing.JTextArea();
        loggingIn = new javax.swing.JPanel();
        mirthHeadingPanel1 = new com.mirth.connect.client.ui.MirthHeadingPanel();
        jLabel5 = new javax.swing.JLabel();
        loginProgress = new javax.swing.JProgressBar();
        status = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        mirthCorpImage1 = new javax.swing.JLabel();
        placeholderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mirth Connect - Login");
        setIconImage(UIConstants.MIRTH_FAVICON.getImage());

        loginMain.setBackground(new java.awt.Color(255, 255, 255));
        loginMain.setName(""); // NOI18N

        closeButton.setText("Exit");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        serverName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverNameActionPerformed(evt);
            }
        });

        jLabel1.setText("Server:");

        jLabel3.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mirth Connect Login");

        javax.swing.GroupLayout mirthHeadingPanel2Layout = new javax.swing.GroupLayout(mirthHeadingPanel2);
        mirthHeadingPanel2.setLayout(mirthHeadingPanel2Layout);
        mirthHeadingPanel2Layout.setHorizontalGroup(
            mirthHeadingPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mirthHeadingPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        mirthHeadingPanel2Layout.setVerticalGroup(
            mirthHeadingPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mirthHeadingPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jLabel6.setText("Password:");

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        mirthCorpImage.setText(" ");

        errorPane.setBorder(null);
        errorPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        errorPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        errorTextArea.setColumns(20);
        errorTextArea.setEditable(false);
        errorTextArea.setFont(new java.awt.Font("Tahoma", 0, 11));
        errorTextArea.setLineWrap(true);
        errorTextArea.setText("There was an error connecting to the server at the specified address. Please verify that the server is up and running.");
        errorTextArea.setWrapStyleWord(true);
        errorTextArea.setEnabled(false);
        errorPane.setViewportView(errorTextArea);

        javax.swing.GroupLayout loginMainLayout = new javax.swing.GroupLayout(loginMain);
        loginMain.setLayout(loginMainLayout);
        loginMainLayout.setHorizontalGroup(
            loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(mirthHeadingPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mirthCorpImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginMainLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(serverName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginMainLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(errorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        loginMainLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {closeButton, loginButton});

        loginMainLayout.setVerticalGroup(
            loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginMainLayout.createSequentialGroup()
                .addComponent(mirthHeadingPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(serverName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(loginButton)
                    .addComponent(mirthCorpImage))
                .addContainerGap())
        );

        loggingIn.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mirth Connect Login");

        javax.swing.GroupLayout mirthHeadingPanel1Layout = new javax.swing.GroupLayout(mirthHeadingPanel1);
        mirthHeadingPanel1.setLayout(mirthHeadingPanel1Layout);
        mirthHeadingPanel1Layout.setHorizontalGroup(
            mirthHeadingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mirthHeadingPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        mirthHeadingPanel1Layout.setVerticalGroup(
            mirthHeadingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mirthHeadingPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        loginProgress.setDoubleBuffered(true);

        status.setText("Please wait: Logging in...");

        mirthCorpImage1.setText(" ");

        placeholderButton.setText("Placeholder");

        javax.swing.GroupLayout loggingInLayout = new javax.swing.GroupLayout(loggingIn);
        loggingIn.setLayout(loggingInLayout);
        loggingInLayout.setHorizontalGroup(
            loggingInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mirthHeadingPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggingInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(loggingInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status)
                .addContainerGap(247, Short.MAX_VALUE))
            .addGroup(loggingInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mirthCorpImage1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                .addComponent(placeholderButton)
                .addContainerGap())
            .addGroup(loggingInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        loggingInLayout.setVerticalGroup(
            loggingInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggingInLayout.createSequentialGroup()
                .addComponent(mirthHeadingPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loggingInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mirthCorpImage1)
                    .addComponent(placeholderButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loggingIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loggingIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // @formatter:on

    private void usernameActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_usernameActionPerformed
    {// GEN-HEADEREND:event_usernameActionPerformed
        loginButtonActionPerformed(null);
    }// GEN-LAST:event_usernameActionPerformed

    private void serverNameActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_serverNameActionPerformed
    {// GEN-HEADEREND:event_serverNameActionPerformed
        loginButtonActionPerformed(null);
    }// GEN-LAST:event_serverNameActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_passwordActionPerformed
    {// GEN-HEADEREND:event_passwordActionPerformed
        loginButtonActionPerformed(null);
    }// GEN-LAST:event_passwordActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_loginButtonActionPerformed
    {// GEN-HEADEREND:event_loginButtonActionPerformed
        errorPane.setVisible(false);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            public Void doInBackground() {
                boolean errorOccurred = false;

                try {
                    String server = serverName.getText();
                    client = new Client(server, PlatformUI.HTTPS_PROTOCOLS, PlatformUI.HTTPS_CIPHER_SUITES);
                    PlatformUI.SERVER_URL = server;

                    // Attempt to login
                    LoginStatus loginStatus = null;
                    try {
                        Map<String, List<String>> customHeaders = new HashMap<String, List<String>>();
                        customHeaders.put(UserServletInterface.LOGIN_SERVER_URL_HEADER, Collections.singletonList(PlatformUI.SERVER_URL));
                        loginStatus = client.getServlet(UserServletInterface.class, null, customHeaders).login(username.getText(), String.valueOf(password.getPassword()));
                    } catch (ClientException ex) {
                        ex.printStackTrace();

                        if (ex instanceof UnauthorizedException) {
                            UnauthorizedException e2 = (UnauthorizedException) ex;
                            if (e2.getResponse() != null && e2.getResponse() instanceof LoginStatus) {
                                loginStatus = (LoginStatus) e2.getResponse();
                            }
                        }

                        // Leave loginStatus null, the error message will be set to the default
                    }

                    // If SUCCESS or SUCCESS_GRACE_PERIOD
                    if ((loginStatus != null) && ((loginStatus.getStatus() == LoginStatus.Status.SUCCESS) || (loginStatus.getStatus() == LoginStatus.Status.SUCCESS_GRACE_PERIOD))) {
                        if (!handleSuccess(loginStatus)) {
                            LoginPanel.getInstance().setVisible(false);
                            LoginPanel.getInstance().initialize(PlatformUI.SERVER_URL, PlatformUI.CLIENT_VERSION, "", "");
                        }
                    } else {
                        // Assume failure unless overridden by a plugin
                        errorOccurred = true;

                        if (loginStatus instanceof ExtendedLoginStatus) {
                            ExtendedLoginStatus extendedLoginStatus = (ExtendedLoginStatus) loginStatus;

                            if (StringUtils.isNotBlank(extendedLoginStatus.getClientPluginClass())) {
                                String updatedUsername = StringUtils.defaultString(loginStatus.getUpdatedUsername(), username.getText());
                                MultiFactorAuthenticationClientPlugin plugin = (MultiFactorAuthenticationClientPlugin) Class.forName(extendedLoginStatus.getClientPluginClass()).newInstance();

                                loginStatus = plugin.authenticate(LoginPanel.this, client, updatedUsername, loginStatus);

                                if ((loginStatus != null) && ((loginStatus.getStatus() == LoginStatus.Status.SUCCESS) || (loginStatus.getStatus() == LoginStatus.Status.SUCCESS_GRACE_PERIOD))) {
                                    errorOccurred = false;
                                    if (!handleSuccess(loginStatus)) {
                                        LoginPanel.getInstance().setVisible(false);
                                        LoginPanel.getInstance().initialize(PlatformUI.SERVER_URL, PlatformUI.CLIENT_VERSION, "", "");
                                    }
                                }
                            }
                        }
                    }

                    if (errorOccurred) {
                        if (loginStatus != null) {
                            errorTextArea.setText(loginStatus.getMessage());
                        } else {
                            errorTextArea.setText(ERROR_MESSAGE);
                        }
                    }
                } catch (Throwable t) {
                    errorOccurred = true;
                    errorTextArea.setText(ERROR_MESSAGE);
                    t.printStackTrace();
                }

                if (errorOccurred) {
                    errorPane.setVisible(true);
                    loggingIn.setVisible(false);
                    loginMain.setVisible(true);
                    loginProgress.setIndeterminate(false);
                    password.grabFocus();
                }

                return null;
            }

            private boolean handleSuccess(LoginStatus loginStatus) throws ClientException {
                try {
                    PublicServerSettings publicServerSettings = client.getPublicServerSettings();
                    
                    if (publicServerSettings.getLoginNotificationEnabled() == true) {
                    	CustomBannerPanelDialog customBannerPanelDialog = new CustomBannerPanelDialog(LoginPanel.getInstance(), "Login Notification", publicServerSettings.getLoginNotificationMessage());
                    	boolean isAccepted = customBannerPanelDialog.isAccepted();
                    	
                    	if (isAccepted == true) {
                    	    client.setUserNotificationAcknowledged(client.getCurrentUser().getId());
                    	}
                    	else {
                    	    return false;
                    	}
                    }
                    
                    String environmentName = publicServerSettings.getEnvironmentName();
                    if (!StringUtils.isBlank(environmentName)) {
                        PlatformUI.ENVIRONMENT_NAME = environmentName;
                    }

                    String serverName = publicServerSettings.getServerName();
                    if (!StringUtils.isBlank(serverName)) {
                        PlatformUI.SERVER_NAME = serverName;
                    } else {
                        PlatformUI.SERVER_NAME = null;
                    }

                    Color defaultBackgroundColor = publicServerSettings.getDefaultAdministratorBackgroundColor();
                    if (defaultBackgroundColor != null) {
                        PlatformUI.DEFAULT_BACKGROUND_COLOR = defaultBackgroundColor;
                    }
                } catch (ClientException e) {
                    PlatformUI.SERVER_NAME = null;
                }

                try {
                    String database = (String) client.getAbout().get("database");
                    if (!StringUtils.isBlank(database)) {
                        PlatformUI.SERVER_DATABASE = database;
                    } else {
                        PlatformUI.SERVER_DATABASE = null;
                    }
                } catch (ClientException e) {
                    PlatformUI.SERVER_DATABASE = null;
                }

                try {
                    Map<String, String[]> map = client.getProtocolsAndCipherSuites();
                    PlatformUI.SERVER_HTTPS_SUPPORTED_PROTOCOLS = map.get(MirthSSLUtil.KEY_SUPPORTED_PROTOCOLS);
                    PlatformUI.SERVER_HTTPS_ENABLED_CLIENT_PROTOCOLS = map.get(MirthSSLUtil.KEY_ENABLED_CLIENT_PROTOCOLS);
                    PlatformUI.SERVER_HTTPS_ENABLED_SERVER_PROTOCOLS = map.get(MirthSSLUtil.KEY_ENABLED_SERVER_PROTOCOLS);
                    PlatformUI.SERVER_HTTPS_SUPPORTED_CIPHER_SUITES = map.get(MirthSSLUtil.KEY_SUPPORTED_CIPHER_SUITES);
                    PlatformUI.SERVER_HTTPS_ENABLED_CIPHER_SUITES = map.get(MirthSSLUtil.KEY_ENABLED_CIPHER_SUITES);
                } catch (ClientException e) {
                }

                PlatformUI.USER_NAME = StringUtils.defaultString(loginStatus.getUpdatedUsername(), username.getText());
                setStatus("Authenticated...");
                new Mirth(client);
                LoginPanel.getInstance().setVisible(false);

                User currentUser = PlatformUI.MIRTH_FRAME.getCurrentUser(PlatformUI.MIRTH_FRAME);
                Properties userPreferences = new Properties();
                Set<String> preferenceNames = new HashSet<String>();
                preferenceNames.add("firstlogin");
                preferenceNames.add("checkForNotifications");
                preferenceNames.add("showNotificationPopup");
                preferenceNames.add("archivedNotifications");
                try {
                    userPreferences = client.getUserPreferences(currentUser.getId(), preferenceNames);

                    // Display registration dialog if it's the user's first time logging in
                    String firstlogin = userPreferences.getProperty("firstlogin");
                    if (firstlogin == null || BooleanUtils.toBoolean(firstlogin)) {
                    	if (Integer.valueOf(currentUser.getId()) == 1) {
                        	// if current user is user 1:
                    		// 	1. check system preferences for user information
                    		// 	2. if system preferences exist, populate screen using currentUser
                        	Preferences preferences = Preferences.userNodeForPackage(Mirth.class);
    						String systemUserInfo = preferences.get("userLoginInfo", null);
    						if (systemUserInfo != null) {
                        		String info[] = systemUserInfo.split(",", 0);
                                currentUser.setUsername(info[0]); 
                            	currentUser.setFirstName(info[1]);
                            	currentUser.setLastName(info[2]);
                            	currentUser.setEmail(info[3]);
                            	currentUser.setCountry(info[4]);
                            	currentUser.setStateTerritory(info[5]);
                            	currentUser.setPhoneNumber(info[6]);
                            	currentUser.setOrganization(info[7]);
                            	currentUser.setRole(info[8]);
                            	currentUser.setIndustry(info[9]);
                            	currentUser.setDescription(info[10]);
                        	}
                    	}
                        FirstLoginDialog firstLoginDialog = new FirstLoginDialog(currentUser);
                        // if leaving the first login dialog without saving
                        if (!firstLoginDialog.getResult()) {
                        	return false;
                        }
                    } else if (loginStatus.getStatus() == LoginStatus.Status.SUCCESS_GRACE_PERIOD) {
                        new ChangePasswordDialog(currentUser, loginStatus.getMessage());
                    }

                    // Check for new notifications from update server if enabled
                    String checkForNotifications = userPreferences.getProperty("checkForNotifications");
                    if (checkForNotifications == null || BooleanUtils.toBoolean(checkForNotifications)) {
                        Set<Integer> archivedNotifications = new HashSet<Integer>();
                        String archivedNotificationString = userPreferences.getProperty("archivedNotifications");
                        if (archivedNotificationString != null) {
                            archivedNotifications = ObjectXMLSerializer.getInstance().deserialize(archivedNotificationString, Set.class);
                        }
                        // Update the Other Tasks pane with the unarchived notification count
                        int unarchivedNotifications = ConnectServiceUtil.getNotificationCount(PlatformUI.SERVER_ID, PlatformUI.SERVER_VERSION, LoadedExtensions.getInstance().getExtensionVersions(), archivedNotifications, PlatformUI.HTTPS_PROTOCOLS, PlatformUI.HTTPS_CIPHER_SUITES);
                        PlatformUI.MIRTH_FRAME.updateNotificationTaskName(unarchivedNotifications);

                        // Display notification dialog if enabled and if there are new notifications
                        String showNotificationPopup = userPreferences.getProperty("showNotificationPopup");
                        if (showNotificationPopup == null || BooleanUtils.toBoolean(showNotificationPopup)) {
                            if (unarchivedNotifications > 0) {
                                new NotificationDialog();
                            }
                        }
                    }
                } catch (ClientException e) {
                    PlatformUI.MIRTH_FRAME.alertThrowable(PlatformUI.MIRTH_FRAME, e);
                }

                ((Frame) PlatformUI.MIRTH_FRAME).sendUsageStatistics();
                
                return true;
            }

            public void done() {}
        };
        worker.execute();

        loggingIn.setVisible(true);
        loginMain.setVisible(false);
        loginProgress.setIndeterminate(true);
    }// GEN-LAST:event_loginButtonActionPerformed

    /**
     * If the button is "Next" instead of "Finish" then it moves on to the next options. Otherwise,
     * it creates the new channel.
     */
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt)// GEN-FIRST:event_closeButtonActionPerformed
    {// GEN-HEADEREND:event_closeButtonActionPerformed
        this.dispose();
        System.exit(0);
    }// GEN-LAST:event_closeButtonActionPerformed

    public void setStatus(String status) {
        this.status.setText("Please wait: " + status);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane errorPane;
    private javax.swing.JTextArea errorTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel loggingIn;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginMain;
    private javax.swing.JProgressBar loginProgress;
    private javax.swing.JLabel mirthCorpImage;
    private javax.swing.JLabel mirthCorpImage1;
    private com.mirth.connect.client.ui.MirthHeadingPanel mirthHeadingPanel1;
    private com.mirth.connect.client.ui.MirthHeadingPanel mirthHeadingPanel2;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton placeholderButton;
    private javax.swing.JTextField serverName;
    private javax.swing.JLabel status;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
