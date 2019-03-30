package projetcalculatrice.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;

import projetcalculatrice.Model.Model;
import projetcalculatrice.Model.NumberList;
import projetcalculatrice.View;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        model.addObserver(view);

        view.setCalcButtonListener(new CalcButtonListener());

    }

    class CalcButtonListener implements ActionListener {
        boolean OperationAlreadyHappened = false;
        ArrayList<Boolean> isOpened = new ArrayList<>();

        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            String computationText = view.getComputationDisplayText();

            ButtonInfo buttonInfo = new ButtonInfo(buttonText);
            StringInfo stringInfo = new StringInfo(computationText);

            if (buttonInfo.isClear) {
                while (!isOpened.isEmpty()) {
                    isOpened.remove(0);
                }
                model.Clear();
                return;
            }

            if (buttonInfo.isDelete) {
                if (stringInfo.isLastCharacterOBracket()) {
                    Object o = isOpened.get(isOpened.size() - 1);
                    isOpened.remove(o);
                } else if (stringInfo.isLastCharacterCBracket()) {
                    isOpened.add(true);

                }
                model.Delete();
                return;
            }

            if (stringInfo.isEmpty) {

                if (buttonInfo.isNumber) {
                    setComputationText(computationText + buttonText);
                } else if (buttonInfo.isDot) {
                    setComputationText(computationText + buttonText);
                } else if (buttonInfo.isOBracket) {
                    try {
                        setComputationText(computationText + buttonText);
                        isOpened.add(true);
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                } else if (buttonInfo.isCBracket) {
                } // do nothing
                else if (buttonInfo.isOperator) {
                } // do nothing
                else if (buttonInfo.isEquals) {
                } // do nothing
                else if (buttonInfo.isOperation) {
                    setComputationText(computationText + buttonText);
                }
                else if (buttonInfo.isPi) {
                    setComputationText(computationText + buttonText);
                }

            } else { // string is NOT empty
                try {
                    if (stringInfo.isLastCharacterNumber) {

                        if (buttonInfo.isNumber) {
                            if (OperationAlreadyHappened) {
                                setComputationText(buttonText);
                                OperationAlreadyHappened = false;
                            } else {
                                setComputationText(computationText + buttonText);
                            }
                        } else if (buttonInfo.isOperator) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isDot) {
                            NumberList numberList = new NumberList();
                            int lastIndexOfPoint = computationText.lastIndexOf(".");
                            int i= lastIndexOfPoint+1;
                            char[] computationTextArray = computationText.toCharArray();
                            boolean doesDotExist = false;
                            // on verifie ici qu'il n'existe pas deja un point dans l'expression
                            if(lastIndexOfPoint!=-1) {
                                doesDotExist=true;
                                while (i <= computationTextArray.length && doesDotExist) {
                                    if (numberList.contains(computationTextArray[i])) {
                                        i++;
                                    } else {
                                        doesDotExist = false;
                                    }
                                }
                            }
                            if (!doesDotExist) {
                                setComputationText(computationText + buttonText);
                            }
                        } else if (buttonInfo.isOBracket) {
                            // do nothing
                        } else if (buttonInfo.isCBracket && !isOpened.isEmpty()) {
                            setComputationText(computationText + buttonText);
                            Object o = isOpened.get(isOpened.size() - 1);
                            isOpened.remove(o);
                        } else if (buttonInfo.isEquals) {
                            while (!isOpened.isEmpty()) {
                                setComputationText(computationText + ")");
                                Object o = isOpened.get(isOpened.size() - 1);
                                isOpened.remove(o);
                            }
                            OperationAlreadyHappened = true;
                            performComputation();
                        }
                    } else if (stringInfo.isLastCharacterOBracket) {
                        if (buttonInfo.isNumber) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isOperator) {
                            if (buttonInfo.isSubstract) {
                                setComputationText(computationText + buttonText);
                            }
                        } else if (buttonInfo.isDot) {
                        } else if (buttonInfo.isOBracket) {
                            setComputationText(computationText + buttonText);
                            isOpened.add(true);
                            System.out.println(isOpened.size());
                        }else if (buttonInfo.isOperation) {
                                setComputationText(computationText + buttonText);

                        } else if (buttonInfo.isCBracket) {
                            // do nothing
                        }else if (buttonInfo.isPi) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isEquals) {
                            // do nothing
                        }

                    } else if (stringInfo.isLastCharacterCBracket) {
                        if (buttonInfo.isOperator) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isCBracket && !isOpened.isEmpty()) {
                            setComputationText(computationText + buttonText);
                            Object o = isOpened.get(isOpened.size());
                            isOpened.remove(o);
                        } else if (buttonInfo.isEquals) {
                            while (!isOpened.isEmpty()) {
                                setComputationText(computationText + ")");
                                Object o = isOpened.get(isOpened.size() - 1);
                                isOpened.remove(o);
                            }
                            OperationAlreadyHappened = true;
                            performComputation();
                        }
                    } else if (stringInfo.isLastCharacterOperator) {
                        System.out.println("ok");
                        if (buttonInfo.isNumber) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isOperator) {
                            if (stringInfo.isPreviousCharOBracket) {
                                setComputationText(computationText.substring(0, stringInfo.lastCharIndex) + buttonText);
                            }
                        } else if (buttonInfo.isDot) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isOBracket) {
                            setComputationText(computationText + buttonText);
                            isOpened.add(true);
                        }else if (buttonInfo.isOperation) {
                            setComputationText(computationText + buttonText);
                        }else if (buttonInfo.isPi) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isEquals) {
                        } // do nothing

                    } else if (stringInfo.isLastCharacterDot) {

                        if (buttonInfo.isNumber) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isOperator) {
                        } // do nothing
                        else if (buttonInfo.isDot) {
                            setComputationText(computationText.substring(0, stringInfo.lastCharIndex));
                        } else if (buttonInfo.isEquals) {
                        } // do nothing

                    }
                    if (stringInfo.isLastCharacterPi) {

                        if (buttonInfo.isNumber) {
                        } else if (buttonInfo.isOperator) {
                            setComputationText(computationText + buttonText);
                        } else if (buttonInfo.isDot) {
                        } else if (buttonInfo.isOBracket) {
                            // do nothing
                        } else if (buttonInfo.isCBracket && !isOpened.isEmpty()) {
                            setComputationText(computationText + buttonText);
                            Object o = isOpened.get(isOpened.size() - 1);
                            isOpened.remove(o);
                        } else if (buttonInfo.isEquals) {
                            while (!isOpened.isEmpty()) {
                                setComputationText(computationText + ")");
                                Object o = isOpened.get(isOpened.size() - 1);
                                isOpened.remove(o);
                            }
                            OperationAlreadyHappened = true;
                            performComputation();
                        }
                    }
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }

        }

        private void performComputation() {
            model.computeString();
        }

        private void setComputationText(String text) {
            model.setComputationText(text);
        }

    }
}