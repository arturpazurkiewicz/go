package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ConfigurationScreen extends JPanel {
    private JComboBox<String> gameComboBox;
    private JCheckBox withBot;
    URL url= ConfigurationScreen.class.getResource("/ManualPage/htpg.htm");
    public ConfigurationScreen(ClientGui clientGui) {
        super();
        this.setBackground(new Color(224, 172, 105));
        this.setLayout(new BorderLayout());
        JPanel north = new JPanel();
        JPanel south = new JPanel();
        JPanel northCenter = new JPanel();
        JPanel northCenterCenter = new JPanel();
        withBot = new JCheckBox();
        this.setVisible(true);

        northCenter.setLayout(new BorderLayout());
        northCenterCenter.setLayout(new BorderLayout(0,20));
        gameComboBox = new JComboBox<>();
        gameComboBox.addItem("19x19");
        gameComboBox.addItem("13x13");
        gameComboBox.addItem("9x9");
        gameComboBox.addItem("5x5");
        JLabel infoBoxLabel = new JLabel("Choose game size  ");
        JLabel infoCheckBoxLabel = new JLabel("     Play with bot  ");
        infoBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
        infoCheckBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
        gameComboBox.setSelectedIndex(0);
        gameComboBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        north.add(infoBoxLabel);
        north.add(northCenter);
        northCenter.add(gameComboBox,BorderLayout.WEST);
        northCenter.add(northCenterCenter,BorderLayout.CENTER);
        northCenterCenter.add(infoCheckBoxLabel,BorderLayout.WEST);
        northCenterCenter.add(withBot,BorderLayout.CENTER);
        this.add(north,BorderLayout.NORTH);
        this.add(south,BorderLayout.SOUTH);
        JButton start = new JButton("Start matching");
        start.addActionListener(e -> {
            int boardSize = 0;
            switch ((String) Objects.requireNonNull(gameComboBox.getSelectedItem())) {
                case "19x19":
                    boardSize =19;
                    break;
                case "13x13":
                    boardSize = 13;
                    break;
                case "9x9":
                    boardSize = 9;
                    break;
                case "5x5":
                    boardSize = 5;
            }
            ServerConnector.getInstance().sendData(new DataPackage(new GameConfig(withBot.isSelected(),boardSize,false), DataPackage.Info.GameConfig));
            clientGui.startBoard(boardSize);
        });
        JEditorPane jep = new JEditorPane();
        jep.setEditable(false);

        try {
            jep.setPage(url);
        }
        catch (IOException d) {
            d.printStackTrace();
            jep.setContentType("text/html");
            jep.setText("<html>Could not load webpage</html>");
        }

        JScrollPane scrollPane = new JScrollPane(jep);
        this.add(scrollPane,BorderLayout.CENTER);
        south.setLayout(new BorderLayout());
        south.add(start,BorderLayout.SOUTH);
    }
}
