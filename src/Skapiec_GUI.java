/**
 * @author <pre>
 *     Szymon Lenart
 *     Damian Bugaj
 *     Bartlomiej Szpila
 *     </pre>
 * @version 6.06.2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * The <code>Skapiec_GUI</code> object
 */
public class Skapiec_GUI {
    private JPanel panel;
    private JSpinner productQuantitySpinner;
    private JSpinner minValueSpinner;
    private JSpinner maxValueSpinner;
    private JComboBox sellersRatingComboBox;
    private JTextField product_4;
    private JTextField product_3;
    private JTextField product_2;
    private JTextField product_5;
    private JTextField product_1;
    private JButton searchButton;
    private JButton nextProductButton;
    private JButton newSearchButton;
    private JTabbedPane tabs;
    private JLabel col_1_product_1;
    private JLabel col_1_product_2;
    private JLabel col_1_product_3;
    private JLabel col_1_product_4;
    private JLabel col_1_product_5;
    private JLabel col_2_product_1;
    private JLabel col_2_product_2;
    private JLabel col_2_product_3;
    private JLabel col_2_product_4;
    private JLabel col_2_product_5;
    private JLabel col_3_product_1;
    private JLabel col_3_product_2;
    private JLabel col_3_product_3;
    private JLabel col_3_product_4;
    private JLabel col_3_product_5;
    private JTextArea first_product_cost;
    private JTextArea second_product_cost;
    private JTextArea third_product_cost;
    private int index = 0;

    private ArrayList<JTextField> productText = new ArrayList<>();
    private ArrayList<String> products = new ArrayList<>();
    private ArrayList<Object> productsQuantity = new ArrayList<Object>();
    private ArrayList<Object> minValues = new ArrayList<Object>();
    private ArrayList<Object> maxValues = new ArrayList<Object>();
    private ArrayList<Object> sellersRatings = new ArrayList<Object>();

    private ArrayList<Scrapper> scrappers = new ArrayList<>();

    private Thread thread1, thread2, thread3, thread4, thread5;

    private MouseListener mouseListener11, mouseListener21, mouseListener31,
            mouseListener12, mouseListener22, mouseListener32,
            mouseListener13, mouseListener23, mouseListener33,
            mouseListener14, mouseListener24, mouseListener34,
            mouseListener15, mouseListener25, mouseListener35;


    /**
     * The default constructor
     */
    public Skapiec_GUI() {
        try {
            setDefaultSettings();
            listener();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Coś poszło nie tak :(");
        }

    }

    /**
     *
     * Start GUI frame
     */

    public static void main(String[] args) {

        JFrame frame = new JFrame("Skapiec");
        frame.setContentPane(new Skapiec_GUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * listener for buttons
     */
    private void listener() {

        tabs.setEnabled(false);
        Collections.addAll(productText, product_1, product_2, product_3, product_4, product_5);

        nextProductButton.addActionListener(e -> {
            switch (index) {
                case 0:
                    if (!productText.get(index).getText().equals("")) {
                        if (checkIfPriceRange() == 1) break;
                        getProductValues(productText.get(index).getText(), productQuantitySpinner.getValue(), minValueSpinner.getValue(),
                                maxValueSpinner.getValue(), sellersRatingComboBox.getSelectedItem());
                        productText.get(index).setEnabled(false);
                        productText.get(index + 1).setEnabled(true);
                        nextProductButton.setEnabled(true);
                        searchButton.setEnabled(true);
                        startSearchThread(productText.get(index).getText(), index, thread1);
                        index++;
                        setDefaultSettings();
                    } else JOptionPane.showMessageDialog(null, "Nie wprowadziłeś nazwy produktu!");
                    break;
                case 1:
                    if (!productText.get(index).getText().equals("")) {
                        if (checkIfPriceRange() == 1) break;
                        getProductValues(productText.get(index).getText(), productQuantitySpinner.getValue(), minValueSpinner.getValue(),
                                maxValueSpinner.getValue(), sellersRatingComboBox.getSelectedItem());
                        productText.get(index).setEnabled(false);
                        productText.get(index + 1).setEnabled(true);
                        startSearchThread(productText.get(index).getText(), index, thread2);
                        index++;
                        setDefaultSettings();

                    } else JOptionPane.showMessageDialog(null, "Nie wprowadziłeś nazwy produktu!");
                    break;
                case 2:
                    if (!productText.get(index).getText().equals("")) {
                        if (checkIfPriceRange() == 1) break;
                        getProductValues(productText.get(index).getText(), productQuantitySpinner.getValue(), minValueSpinner.getValue(),
                                maxValueSpinner.getValue(), sellersRatingComboBox.getSelectedItem());
                        productText.get(index).setEnabled(false);
                        productText.get(index + 1).setEnabled(true);
                        startSearchThread(productText.get(index).getText(), index, thread3);
                        index++;
                        setDefaultSettings();

                    } else JOptionPane.showMessageDialog(null, "Nie wprowadziłeś nazwy produktu!");
                    break;
                case 3:
                    if (!productText.get(index).getText().equals("")) {
                        if (checkIfPriceRange() == 1) break;
                        getProductValues(productText.get(index).getText(), productQuantitySpinner.getValue(), minValueSpinner.getValue(),
                                maxValueSpinner.getValue(), sellersRatingComboBox.getSelectedItem());
                        productText.get(index).setEnabled(false);
                        productText.get(index + 1).setEnabled(true);
                        startSearchThread(productText.get(index).getText(), index, thread4);
                        index++;
                        setDefaultSettings();
                    } else JOptionPane.showMessageDialog(null, "Nie wprowadziłeś nazwy produktu!");
                    break;
                case 4:
                    if (!productText.get(index).getText().equals("")) {
                        if (checkIfPriceRange() == 1) break;
                        getProductValues(productText.get(index).getText(), productQuantitySpinner.getValue(), minValueSpinner.getValue(),
                                maxValueSpinner.getValue(), sellersRatingComboBox.getSelectedItem());
                        productText.get(index).setEnabled(false);
                        nextProductButton.setEnabled(false);
                        startSearchThread(productText.get(index).getText(), index, thread5);
                        index++;
                        setDefaultSettings();
                    } else JOptionPane.showMessageDialog(null, "Nie wprowadziłeś nazwy produktu!");
                    break;

            }

        });


        searchButton.addActionListener(e -> {
            String message = "Nie zatwierdziłeś ostatniego produktu, czy na pewno chcesz kontynuować?";
            String waitMessage = "Proszę czekać, trwa wyszukiwanie";
            ArrayList<Product> products1 = new ArrayList<>();
            ArrayList<Product> products2 = new ArrayList<>();
            ArrayList<Product> products3 = new ArrayList<>();
            ArrayList<Product> products4 = new ArrayList<>();
            ArrayList<Product> products5 = new ArrayList<>();

            int dialogChoice = 0;
            final int newIndex = index;
            try {
                switch (newIndex) {
                    case 1:
                        dialogChoice = setTabs(newIndex, message);
                        if (dialogChoice == 0) {
                            try {
                                TimeUnit.SECONDS.sleep(14);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            nextProductButton.setEnabled(false);
                            product_2.setEnabled(false);
                            searchButton.setEnabled(false);
                            products1.add(new Product(scrappers.get(newIndex - 1), newIndex - 1));
                            products1.add(new Product(scrappers.get(newIndex - 1), newIndex));
                            products1.add(new Product(scrappers.get(newIndex - 1), newIndex + 1));

                            SortingAlgorithm sortingAlgorithm1 = new SortingAlgorithm(products1, (int) productsQuantity.get(index - 1));

                            first_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm1.getFirstPrice() + "zł");
                            second_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm1.getSecondPrice() + "zł");
                            third_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm1.getThirdPrice() + "zł");

                            col_1_product_1.addMouseListener(mouseListener11 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm1.getSellerLinks1().get(newIndex - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm1.getProductNames1().get(newIndex - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_1.setText(String.valueOf(sortingAlgorithm1.getProductNames1().get(newIndex - 1)));
                                }
                            });
                            col_2_product_1.addMouseListener(mouseListener21 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm1.getSellerLinks2().get(newIndex - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm1.getProductNames2().get(newIndex - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_1.setText(String.valueOf(sortingAlgorithm1.getProductNames2().get(newIndex - 1)));
                                }
                            });

                            col_3_product_1.addMouseListener(mouseListener31 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm1.getSellerLinks3().get(newIndex - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm1.getProductNames3().get(newIndex - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_1.setText(String.valueOf(sortingAlgorithm1.getProductNames3().get(newIndex - 1)));
                                }
                            });

                            col_1_product_1.setText(String.valueOf(sortingAlgorithm1.getProductNames1().get(newIndex - 1)));
                            col_2_product_1.setText(String.valueOf(sortingAlgorithm1.getProductNames2().get(newIndex - 1)));
                            col_3_product_1.setText(String.valueOf(sortingAlgorithm1.getProductNames3().get(newIndex - 1)));
                        }
                        break;
                    case 2:
                        dialogChoice = setTabs(newIndex, message);
                        if (dialogChoice == 0) {
                            try {
                                TimeUnit.SECONDS.sleep(14);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            nextProductButton.setEnabled(false);
                            product_3.setEnabled(false);
                            searchButton.setEnabled(false);
                            products1.add(new Product(scrappers.get(index - 2), index - 2));
                            products1.add(new Product(scrappers.get(index - 2), index - 1));
                            products1.add(new Product(scrappers.get(index - 2), index));
                            products2.add(new Product(scrappers.get(index - 1), index - 2));
                            products2.add(new Product(scrappers.get(index - 1), index - 1));
                            products2.add(new Product(scrappers.get(index - 1), index));

                            SortingAlgorithm sortingAlgorithm2 = new SortingAlgorithm(products1, (int) productsQuantity.get(index - 2), products2, (int) productsQuantity.get(index - 1));

                            first_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm2.getFirstPrice() + "zł");
                            second_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm2.getSecondPrice() + "zł");
                            third_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm2.getThirdPrice() + "zł");


                            col_1_product_1.addMouseListener(mouseListener11 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm2.getSellerLinks1().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm2.getProductNames1().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_1.setText(String.valueOf(sortingAlgorithm2.getProductNames1().get(index - 2)));
                                }
                            });
                            col_2_product_1.addMouseListener(mouseListener21 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm2.getSellerLinks2().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm2.getProductNames2().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_1.setText(String.valueOf(sortingAlgorithm2.getProductNames2().get(index - 2)));
                                }
                            });
                            col_3_product_1.addMouseListener(mouseListener31 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm2.getSellerLinks3().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm2.getProductNames3().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_1.setText(String.valueOf(sortingAlgorithm2.getProductNames3().get(index - 2)));
                                }
                            });
                            col_1_product_2.addMouseListener(mouseListener12 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm2.getSellerLinks1().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm2.getProductNames1().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_2.setText(String.valueOf(sortingAlgorithm2.getProductNames1().get(index - 1)));
                                }
                            });
                            col_2_product_2.addMouseListener(mouseListener22 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm2.getSellerLinks2().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm2.getProductNames2().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_2.setText(String.valueOf(sortingAlgorithm2.getProductNames2().get(index - 1)));
                                }
                            });
                            col_3_product_2.addMouseListener(mouseListener32 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm2.getSellerLinks3().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm2.getProductNames3().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_2.setText(String.valueOf(sortingAlgorithm2.getProductNames3().get(index - 1)));
                                }
                            });

                            col_1_product_1.setText(String.valueOf(sortingAlgorithm2.getProductNames1().get(index - 2)));
                            col_2_product_1.setText(String.valueOf(sortingAlgorithm2.getProductNames2().get(index - 2)));
                            col_3_product_1.setText(String.valueOf(sortingAlgorithm2.getProductNames3().get(index - 2)));

                            col_1_product_2.setText(String.valueOf(sortingAlgorithm2.getProductNames1().get(index - 1)));
                            col_2_product_2.setText(String.valueOf(sortingAlgorithm2.getProductNames2().get(index - 1)));
                            col_3_product_2.setText(String.valueOf(sortingAlgorithm2.getProductNames3().get(index - 1)));
                        }
                        break;
                    case 3:
                        dialogChoice = setTabs(newIndex, message);
                        if (dialogChoice == 0) {
                            try {
                                TimeUnit.SECONDS.sleep(14);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            nextProductButton.setEnabled(false);
                            product_4.setEnabled(false);
                            searchButton.setEnabled(false);
                            products1.add(new Product(scrappers.get(index - 3), index - 3));
                            products1.add(new Product(scrappers.get(index - 3), index - 2));
                            products1.add(new Product(scrappers.get(index - 3), index - 1));
                            products2.add(new Product(scrappers.get(index - 2), index - 3));
                            products2.add(new Product(scrappers.get(index - 2), index - 2));
                            products2.add(new Product(scrappers.get(index - 2), index - 1));
                            products3.add(new Product(scrappers.get(index - 1), index - 3));
                            products3.add(new Product(scrappers.get(index - 1), index - 2));
                            products3.add(new Product(scrappers.get(index - 1), index - 1));
                            SortingAlgorithm sortingAlgorithm3 = new SortingAlgorithm(products1, (int) productsQuantity.get(index - 3),
                                    products2, (int) productsQuantity.get(index - 2),
                                    products3, (int) productsQuantity.get(index - 1));

                            first_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm3.getFirstPrice() + "zł");
                            second_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm3.getSecondPrice() + "zł");
                            third_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm3.getThirdPrice() + "zł");

                            col_1_product_1.addMouseListener(mouseListener11 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks1().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_1.setText(String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 3)));
                                }
                            });
                            col_2_product_1.addMouseListener(mouseListener21 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks2().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_1.setText(String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 3)));
                                }
                            });
                            col_3_product_1.addMouseListener(mouseListener31 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks3().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_1.setText(String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 3)));
                                }
                            });
                            col_1_product_2.addMouseListener(mouseListener12 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks1().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_2.setText(String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 2)));
                                }
                            });
                            col_2_product_2.addMouseListener(mouseListener22 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks2().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_2.setText(String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 2)));
                                }
                            });
                            col_3_product_2.addMouseListener(mouseListener32 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks3().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_2.setText(String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 2)));
                                }
                            });
                            col_1_product_3.addMouseListener(mouseListener13 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks1().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_3.setText(String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 1)));
                                }
                            });
                            col_2_product_3.addMouseListener(mouseListener23 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks2().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_3.setText(String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 1)));
                                }
                            });
                            col_3_product_3.addMouseListener(mouseListener33 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm3.getSellerLinks3().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_3.setText(String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 1)));
                                }
                            });

                            col_1_product_1.setText(String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 3)));
                            col_2_product_1.setText(String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 3)));
                            col_3_product_1.setText(String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 3)));

                            col_1_product_2.setText(String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 2)));
                            col_2_product_2.setText(String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 2)));
                            col_3_product_2.setText(String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 2)));

                            col_1_product_3.setText(String.valueOf(sortingAlgorithm3.getProductNames1().get(index - 1)));
                            col_2_product_3.setText(String.valueOf(sortingAlgorithm3.getProductNames2().get(index - 1)));
                            col_3_product_3.setText(String.valueOf(sortingAlgorithm3.getProductNames3().get(index - 1)));
                        }
                        break;
                    case 4:
                        dialogChoice = setTabs(newIndex, message);
                        if (dialogChoice == 0) {
                            try {
                                TimeUnit.SECONDS.sleep(14);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            nextProductButton.setEnabled(false);
                            product_5.setEnabled(false);
                            searchButton.setEnabled(false);
                            products1.add(new Product(scrappers.get(index - 4), index - 4));
                            products1.add(new Product(scrappers.get(index - 4), index - 3));
                            products1.add(new Product(scrappers.get(index - 4), index - 2));
                            products2.add(new Product(scrappers.get(index - 3), index - 4));
                            products2.add(new Product(scrappers.get(index - 3), index - 3));
                            products2.add(new Product(scrappers.get(index - 3), index - 2));
                            products3.add(new Product(scrappers.get(index - 2), index - 4));
                            products3.add(new Product(scrappers.get(index - 2), index - 3));
                            products3.add(new Product(scrappers.get(index - 2), index - 2));
                            products4.add(new Product(scrappers.get(index - 1), index - 4));
                            products4.add(new Product(scrappers.get(index - 1), index - 3));
                            products4.add(new Product(scrappers.get(index - 1), index - 2));

                            SortingAlgorithm sortingAlgorithm4 = new SortingAlgorithm(products1, (int) productsQuantity.get(index - 4),
                                    products2, (int) productsQuantity.get(index - 3),
                                    products3, (int) productsQuantity.get(index - 2),
                                    products4, (int) productsQuantity.get(index - 1));

                            first_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm4.getFirstPrice() + "zł");
                            second_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm4.getSecondPrice() + "zł");
                            third_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm4.getThirdPrice() + "zł");

                            col_1_product_1.addMouseListener(mouseListener11 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks1().get(index - 4)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 4)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_1.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 4)));
                                }
                            });
                            col_2_product_1.addMouseListener(mouseListener21 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks2().get(index - 4)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 4)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_1.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 4)));
                                }
                            });
                            col_3_product_1.addMouseListener(mouseListener31 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks3().get(index - 4)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 4)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_1.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 4)));
                                }
                            });
                            col_1_product_2.addMouseListener(mouseListener12 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks1().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_2.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 3)));
                                }
                            });
                            col_2_product_2.addMouseListener(mouseListener22 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks2().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_2.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 3)));
                                }
                            });
                            col_3_product_2.addMouseListener(mouseListener32 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks3().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_2.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 3)));
                                }
                            });
                            col_1_product_3.addMouseListener(mouseListener13 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks1().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_3.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 2)));
                                }
                            });
                            col_2_product_3.addMouseListener(mouseListener23 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks2().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_3.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 2)));
                                }
                            });
                            col_3_product_3.addMouseListener(mouseListener33 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks3().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_3.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 2)));
                                }
                            });
                            col_1_product_4.addMouseListener(mouseListener14 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks1().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_4.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_4.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 1)));
                                }
                            });
                            col_2_product_4.addMouseListener(mouseListener24 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks2().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_4.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_4.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 1)));
                                }
                            });
                            col_3_product_4.addMouseListener(mouseListener34 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm4.getSellerLinks3().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_4.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_4.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 1)));
                                }
                            });

                            col_1_product_1.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 4)));
                            col_2_product_1.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 4)));
                            col_3_product_1.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 4)));

                            col_1_product_2.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 3)));
                            col_2_product_2.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 3)));
                            col_3_product_2.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 3)));

                            col_1_product_3.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 2)));
                            col_2_product_3.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 2)));
                            col_3_product_3.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 2)));

                            col_1_product_4.setText(String.valueOf(sortingAlgorithm4.getProductNames1().get(index - 1)));
                            col_2_product_4.setText(String.valueOf(sortingAlgorithm4.getProductNames2().get(index - 1)));
                            col_3_product_4.setText(String.valueOf(sortingAlgorithm4.getProductNames3().get(index - 1)));
                        }
                        break;
                    case 5:
                        dialogChoice = setTabs(newIndex, message);
                        if (dialogChoice == 0) {
                            try {
                                TimeUnit.SECONDS.sleep(15);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            tabs.setSelectedIndex(1);
                            tabs.setEnabled(true);
                            searchButton.setEnabled(false);
                            products1.add(new Product(scrappers.get(index - 5), index - 5));
                            products1.add(new Product(scrappers.get(index - 5), index - 4));
                            products1.add(new Product(scrappers.get(index - 5), index - 3));
                            products2.add(new Product(scrappers.get(index - 4), index - 5));
                            products2.add(new Product(scrappers.get(index - 4), index - 4));
                            products2.add(new Product(scrappers.get(index - 4), index - 3));
                            products3.add(new Product(scrappers.get(index - 3), index - 5));
                            products3.add(new Product(scrappers.get(index - 3), index - 4));
                            products3.add(new Product(scrappers.get(index - 3), index - 3));
                            products4.add(new Product(scrappers.get(index - 2), index - 5));
                            products4.add(new Product(scrappers.get(index - 2), index - 4));
                            products4.add(new Product(scrappers.get(index - 2), index - 3));
                            products5.add(new Product(scrappers.get(index - 1), index - 5));
                            products5.add(new Product(scrappers.get(index - 1), index - 4));
                            products5.add(new Product(scrappers.get(index - 1), index - 3));

                            SortingAlgorithm sortingAlgorithm5 = new SortingAlgorithm(products1, (int) productsQuantity.get(index - 5),
                                    products2, (int) productsQuantity.get(index - 4),
                                    products3, (int) productsQuantity.get(index - 3),
                                    products4, (int) productsQuantity.get(index - 2),
                                    products5, (int) productsQuantity.get(index - 1));

                            first_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm5.getFirstPrice() + "zł");
                            second_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm5.getSecondPrice() + "zł");
                            third_product_cost.setText("Sumaryczna cena: " + sortingAlgorithm5.getThirdPrice() + "zł");

                            col_1_product_1.addMouseListener(mouseListener11 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks1().get(index - 5)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 5)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_1.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 5)));
                                }
                            });
                            col_2_product_1.addMouseListener(mouseListener21 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks2().get(index - 5)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 5)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_1.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 5)));
                                }
                            });
                            col_3_product_1.addMouseListener(mouseListener31 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks3().get(index - 5)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_1.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 5)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_1.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 5)));
                                }
                            });
                            col_1_product_2.addMouseListener(mouseListener12 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks1().get(index - 4)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 4)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_2.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 4)));
                                }
                            });
                            col_2_product_2.addMouseListener(mouseListener22 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks2().get(index - 4)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 4)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_2.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 4)));
                                }
                            });
                            col_3_product_2.addMouseListener(mouseListener32 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks3().get(index - 4)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_2.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 4)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_2.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 4)));
                                }
                            });
                            col_1_product_3.addMouseListener(mouseListener13 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks1().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_3.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 3)));
                                }
                            });
                            col_2_product_3.addMouseListener(mouseListener23 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks2().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_3.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 3)));
                                }
                            });
                            col_3_product_3.addMouseListener(mouseListener33 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks3().get(index - 3)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_3.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 3)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_3.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 3)));
                                }
                            });
                            col_1_product_4.addMouseListener(mouseListener14 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks1().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_4.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_4.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 2)));
                                }
                            });
                            col_2_product_4.addMouseListener(mouseListener24 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks2().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_4.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_4.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 2)));
                                }
                            });
                            col_3_product_4.addMouseListener(mouseListener34 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks3().get(index - 2)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_4.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 2)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_4.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 2)));
                                }
                            });
                            col_1_product_5.addMouseListener(mouseListener15 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks1().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_1_product_5.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_1_product_5.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 1)));
                                }
                            });
                            col_2_product_5.addMouseListener(mouseListener25 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks2().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_2_product_5.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_2_product_5.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 1)));
                                }
                            });
                            col_3_product_5.addMouseListener(mouseListener35 = new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    try {
                                        Desktop.getDesktop().browse(URI.create(sortingAlgorithm5.getSellerLinks3().get(index - 1)));
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    col_3_product_5.setText("<html><a href=''>" + String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 1)) + "</a></html>");
                                }

                                @Override
                                public void mouseExited(MouseEvent e) {
                                    col_3_product_5.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 1)));
                                }
                            });

                            col_1_product_1.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 5)));
                            col_2_product_1.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 5)));
                            col_3_product_1.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 5)));

                            col_1_product_2.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 4)));
                            col_2_product_2.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 4)));
                            col_3_product_2.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 4)));

                            col_1_product_3.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 3)));
                            col_2_product_3.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 3)));
                            col_3_product_3.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 3)));

                            col_1_product_4.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 2)));
                            col_2_product_4.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 2)));
                            col_3_product_4.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 2)));

                            col_1_product_5.setText(String.valueOf(sortingAlgorithm5.getProductNames1().get(index - 1)));
                            col_2_product_5.setText(String.valueOf(sortingAlgorithm5.getProductNames2().get(index - 1)));
                            col_3_product_5.setText(String.valueOf(sortingAlgorithm5.getProductNames3().get(index - 1)));
                        }
                        break;
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Coś poszło nie tak :(");
                tabs.setSelectedIndex(0);
                setDefaultSettings();
            }

        });

        newSearchButton.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz rozpocząć nowe wyszukiwanie?", "Warning", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                setDefaultSettings();
                searchButton.setEnabled(false);
                nextProductButton.setEnabled(true);
                index = 0;
                productText.get(index).setEnabled(true);
                for (JTextField i : productText) {
                    i.setText(null);
                }
                for (int i = 1; i < productText.size(); i++) {
                    productText.get(i).setEnabled(false);
                }

                first_product_cost.setText("");
                second_product_cost.setText("");
                third_product_cost.setText("");

                col_1_product_1.setText("");
                col_2_product_1.setText("");
                col_3_product_1.setText("");

                col_1_product_2.setText("");
                col_2_product_2.setText("");
                col_3_product_2.setText("");

                col_1_product_3.setText("");
                col_2_product_3.setText("");
                col_3_product_3.setText("");

                col_1_product_4.setText("");
                col_2_product_4.setText("");
                col_3_product_4.setText("");

                col_1_product_5.setText("");
                col_2_product_5.setText("");
                col_3_product_5.setText("");

                scrappers.clear();

                col_1_product_1.removeMouseListener(mouseListener11);
                col_2_product_1.removeMouseListener(mouseListener21);
                col_3_product_1.removeMouseListener(mouseListener31);

                col_1_product_2.removeMouseListener(mouseListener12);
                col_2_product_2.removeMouseListener(mouseListener22);
                col_3_product_2.removeMouseListener(mouseListener32);

                col_1_product_3.removeMouseListener(mouseListener13);
                col_2_product_3.removeMouseListener(mouseListener23);
                col_3_product_3.removeMouseListener(mouseListener33);

                col_1_product_4.removeMouseListener(mouseListener14);
                col_2_product_4.removeMouseListener(mouseListener24);
                col_3_product_4.removeMouseListener(mouseListener34);

                col_1_product_5.removeMouseListener(mouseListener15);
                col_2_product_5.removeMouseListener(mouseListener25);
                col_3_product_5.removeMouseListener(mouseListener35);

                productsQuantity.clear();
                sellersRatings.clear();
            }
        });

    }

    /**
     * Set spinner's value to default
     */
    private void setDefaultSettings() {
        tabs.setEnabled(false);
        productQuantitySpinner.setModel(new SpinnerNumberModel(1, 1, 10000000, 1));
        minValueSpinner.setModel(new SpinnerNumberModel(0, 0, 10000000, 0.01));
        maxValueSpinner.setModel(new SpinnerNumberModel(10000, 0, 10000000, 0.01));
    }

    /**
     * Method to get product's details
     * @param name
     * @param quantity
     * @param minValue
     * @param maxValue
     * @param sellerRating
     */
    private void getProductValues(String name, Object quantity, Object minValue, Object maxValue, Object sellerRating) {
        products.add(name);
        productsQuantity.add(quantity);
        minValues.add(minValue);
        maxValues.add(maxValue);
        sellersRatings.add(sellerRating);
    }


    /**
     *
     * @return 1 if max price is lower than  min or 0 if is not
     */
    private int checkIfPriceRange() {
        if (Double.parseDouble(String.valueOf(minValueSpinner.getValue())) > Double.parseDouble(String.valueOf(maxValueSpinner.getValue()))) {
            JOptionPane.showMessageDialog(null, "Cena maksymalna jest mniejsza od minimalnej, nie można zatwierdzić produktu!");
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Method to check if entered text is confirmed
     * @param index area number to put text
     * @param message warning message
     * @return clicked option
     */
    private int setTabs(int index, String message) {
        int input = 0;
        if (!productText.get(index).getText().equals("")) {
            input = JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                tabs.setSelectedIndex(1);
                tabs.setEnabled(true);
                searchButton.setEnabled(false);
            }
        } else {
            tabs.setSelectedIndex(1);
            tabs.setEnabled(true);
            searchButton.setEnabled(false);
        }
        return input;
    }

    /**
     * @param searchString searched name of product
     * @param index
     * @param thread the <code>Thread</code> object
     * @throws InterruptedException
     */
/*    private void startSearchThread(String searchString, int index, Thread thread) {
        final int threadIndex = index;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    scrappers.add(new Scrapper(searchString));
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    trimTables(scrappers.get(threadIndex), Double.parseDouble((String) sellersRatingComboBox.getSelectedItem()),
                                            (double) minValueSpinner.getValue(), (double) maxValueSpinner.getValue());
                                }
                            },
                            1000
                    );
                } catch (IOException e) {
                    //e.printStackTrace();
                } catch (IndexOutOfBoundsException e1){
                    //
                }
            }
        });
        thread.start();
    }*/
    private void startSearchThread(String searchString, int index, Thread thread) {
        final int threadIndex = index;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    scrappers.add(new Scrapper(searchString));
                    trimTables(scrappers.get(threadIndex), Double.parseDouble((String) sellersRatingComboBox.getSelectedItem()),
                            (double)minValueSpinner.getValue(), (double)maxValueSpinner.getValue());
                } catch (IOException e) {

                } catch (IndexOutOfBoundsException e1){

                }
            }
        });
        thread.start();
    }

    /**
     * Method to remove items with improper details
     * @param scrapper scrap product's details
     * @param rating product's combobox rating
     * @param minValue min product's spinner value
     * @param maxValue max product's spinner value
     */
    private void trimTables(Scrapper scrapper, Double rating, Double minValue, Double maxValue) {
        for (int i = 0; i < scrapper.getRatings().size(); i++) {
            if (scrapper.getRatings().get(i) < rating) {
                scrapper.getRatings().remove(i);
                scrapper.getRatingsCount().remove(i);
                scrapper.getDeliveryCosts().remove(i);
                scrapper.getPrices().remove(i);
                scrapper.getProductNames().remove(i);
                scrapper.getSellers().remove(i);
                scrapper.getSellerLinks().remove(i);
            } else if (scrapper.getDeliveryCosts().get(i) < 0){
                scrapper.getRatings().remove(i);
                scrapper.getRatingsCount().remove(i);
                scrapper.getDeliveryCosts().remove(i);
                scrapper.getPrices().remove(i);
                scrapper.getProductNames().remove(i);
                scrapper.getSellers().remove(i);
                scrapper.getSellerLinks().remove(i);
            }

        }
        for (int i = 0; i < scrapper.getRatings().size(); i++) {
            if (scrapper.getPrices().get(i) > maxValue || scrapper.getPrices().get(i) < minValue) {
                scrapper.getRatings().remove(i);
                scrapper.getRatingsCount().remove(i);
                scrapper.getDeliveryCosts().remove(i);
                scrapper.getPrices().remove(i);
                scrapper.getProductNames().remove(i);
                scrapper.getSellers().remove(i);
                scrapper.getSellerLinks().remove(i);
            }
        }

    }
}