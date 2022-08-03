import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.Event;

// �d��̃v���O����
public class Sample1 extends Application{
	// baseNumber �ɑ΂��� nextNumber �𑫂�����������肷��B
	int baseNumber=0;
	int nextNumber=0;
	// �������������� operator �ŏꍇ�����ł���悤�ɂ���
	String operator="���";
	// �d��ɔz�u����{�^����f�B�X�v���C�̍쐬
	TextField displayField = new TextField();			// ���l�f�B�X�v���C�B
	TextField operatorField = new TextField(operator);	// ���Z�q�f�B�X�v���C�B
	Button[] numberButton = new Button[10];		// 0~9�{�^��
	Button equalButton = new Button("��");		// "="�{�^��
	Button plusButton = new Button("�{");		// "+"�{�^��
	Button minusButton = new Button("�[");		// "-"�{�^���ǉ�
	Button kakeButton = new Button("*");		// "*"�{�^���ǉ�
	// start���\�b�h
	public void start(Stage stage){
		// �{�^����z�u����y�C��
		GridPane pane1 = new GridPane();
		// 0~9�{�^��
		for(int i=0;i<numberButton.length;i++){
			numberButton[i] = new Button(""+i);
			numberButton[i].setPrefSize(40,40);
			numberButton[i].setOnAction(event -> pressedNumberButton(event));
			pane1.getChildren().add(numberButton[i]);
		}
		// "=","+","-"�{�^��
		equalButton.setPrefSize(40,40);
		plusButton.setPrefSize(40,40);
		minusButton.setPrefSize(40,40);		//�ǉ�
		kakeButton.setPrefSize(40,40);		//�ǉ�
		equalButton.setOnAction(event -> pressedEqualButton());
		plusButton.setOnAction(event -> pressedPlusButton());
		minusButton.setOnAction(event -> pressedMinusButton());		//�ǉ�
		kakeButton.setOnAction(event -> pressedKakeButton());		//�ǉ�
		pane1.getChildren().add(equalButton);
		pane1.getChildren().add(plusButton);
		pane1.getChildren().add(minusButton);		//�ǉ�
		pane1.getChildren().add(kakeButton);		//�ǉ�
		// 0~9�{�^���̍��W����
		pane1.setConstraints(numberButton[0],0,3);
		pane1.setConstraints(numberButton[1],0,2);
		pane1.setConstraints(numberButton[2],1,2);
		pane1.setConstraints(numberButton[3],2,2);
		pane1.setConstraints(numberButton[4],0,1);
		pane1.setConstraints(numberButton[5],1,1);
		pane1.setConstraints(numberButton[6],2,1);
		pane1.setConstraints(numberButton[7],0,0);
		pane1.setConstraints(numberButton[8],1,0);
		pane1.setConstraints(numberButton[9],2,0);
		pane1.setConstraints(equalButton,3,3);
		pane1.setConstraints(plusButton,3,2);
		pane1.setConstraints(minusButton,3,1);
		pane1.setConstraints(kakeButton,3,0);
		
		// ���l�Ȃǂ�\������f�B�X�v���C
		FlowPane pane2 = new FlowPane(); //�f�B�X�v���C��z�u����y�C��
		pane2.setPrefSize(160,40);
		displayField.setPrefSize(120,40);
		operatorField.setPrefSize(40,40);
		pane2.getChildren().add(displayField);
		pane2.getChildren().add(operatorField);
		// �f�B�X�v���C�ƃ{�^���y�C�������[�g�y�C���֔z�u
		VBox pane = new VBox();
		pane.getChildren().add(pane2);
		pane.getChildren().add(pane1);
		
		// �E�B���h�E�\����
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
	// �����\�b�h
	// �f�B�X�v���C�ɐ��l�Ɖ��Z�q��\��
	void display(int n){
		displayField.setText(""+n);
		operatorField.setText(operator);
	}
	// �v�Z�̊m��
	void calclate(){
		// operator�̒l�ɉ����Čv�Z���@���ꍇ����
		if(operator.equals("���")){
			baseNumber = nextNumber;
		}else if(operator.equals("�{")){
			baseNumber += nextNumber;
		}else if(operator.equals("�|")){ 	//�ǉ�
			baseNumber -= nextNumber;
		}else if(operator.equals("*")){ 	//�ǉ�
			baseNumber *= nextNumber;
		}
		nextNumber = 0;		// nextNumber�̃��Z�b�g
		display(baseNumber);// baseNumber�̕\��
	}
	// 0~9�{�^���������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void pressedNumberButton(Event event){
		for(int i=0;i<numberButton.length;i++){
			// �����ꂽ�{�^����numberButton[i]���ǂ����𔻒肷��if��
			if(event.getSource()==numberButton[i]){
				System.out.println("["+i+"]��������܂����B");
				nextNumber = nextNumber*10+i;
				display(nextNumber);
			}
		}
	}
	// "��"�{�^���������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void pressedEqualButton(){
		System.out.println("[��]��������܂����B");
		calclate();
		operator = "���";
		display(baseNumber);
	}
	// "�{"�{�^���������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void pressedPlusButton(){
		System.out.println("[�{]��������܂����B");
		calclate();
		operator = "�{";
	}
	// "-"�{�^���������ꂽ�Ƃ��Ɏ��s����郁�\�b�h	�ǉ�
	void pressedMinusButton(){
		System.out.println("[�|]��������܂����B");
		calclate();
		operator = "�|";
	}
	// "*"�{�^���������ꂽ�Ƃ��Ɏ��s����郁�\�b�h	�ǉ�
	void pressedKakeButton(){
		System.out.println("[*]��������܂����B");
		calclate();
		operator = "*";
	}
	// main���\�b�h
	public static void main(String args[]){
		launch();
	}
}
