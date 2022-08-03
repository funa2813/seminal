import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.Event;
import javafx.scene.input.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// �y�C���g�̃v���O����
public class Sample2 extends Application{
	// �L�����o�X�̐ݒ�
	Canvas canvas = new Canvas(800,600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	// �`�悷��}�`���Ǘ�����ϐ��iFigure�͉��Ő錾�����I���W�i���̃N���X�j
	Figure figure = new Figure();
	// �{�^�����̃R���g���[���̍쐬
	ChoiceBox<String> colorChoice = new ChoiceBox<>();
	ChoiceBox<String> shapeChoice = new ChoiceBox<>();
	Button button = new Button("�N���A�{�^��");//���g�p�i���R�Ɏg���Ă��������j
	Slider slider = new Slider(0,100,50);//���g�p�i���R�Ɏg���Ă��������j
	// start���\�b�h
	public void start(Stage stage){
		// �L�����o�X�ɑ΂���}�E�X�̃C�x���g�n���h���̓o�^
		canvas.setOnMouseClicked(event -> mouseClicked(event));
		canvas.setOnMouseEntered(event -> mouseEntered(event));
		canvas.setOnMouseExited(event -> mouseExited(event));
		canvas.setOnMouseDragged(event -> mouseDragged(event));
		canvas.setOnMouseMoved(event -> mouseMoved(event));
		canvas.setOnMousePressed(event -> mousePressed(event));
		canvas.setOnMouseReleased(event -> mouseReleased(event));
		
		// �R���g���[���ɑ΂���C�x���g�n���h���̓o�^
		button.setOnAction(event -> buttonPressed());
		slider.setOnMouseClicked(event -> sliderClicked());
		slider.setOnMousePressed(event -> sliderPressed());
		slider.setOnMouseReleased(event -> sliderReleased());
		
		// �`���C�X�{�b�N�X�̐ݒ�
		colorChoice.getItems().add("��");
		colorChoice.getItems().add("��");
		colorChoice.getItems().add("��");	//�ǉ�
		colorChoice.setValue("��");
		shapeChoice.getItems().add("�~");
		shapeChoice.getItems().add("�l�p");	//�ǉ�
		shapeChoice.setValue("�~");
		
		// �R���g���[���̔z�u
		FlowPane pane1 = new FlowPane();//�R���g���[����z�u����y�C��
		pane1.getChildren().add(colorChoice);
		pane1.getChildren().add(shapeChoice);
		pane1.getChildren().add(button);
		pane1.getChildren().add(slider);
		
		// �R���g���[���̃y�C���ƃL�����o�X�����[�g�y�C���֔z�u
		VBox pane = new VBox();
		pane.getChildren().add(pane1);
		pane.getChildren().add(canvas);
		
		// �E�B���h�E�̕\����
		Scene scene = new Scene(pane);
		stage.setTitle("�y�C���g");//�^�C�g���̐ݒ�
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
	// �N���b�N���ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void mouseClicked(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("�N���b�N����܂����B");
		System.out.println("\t\t���W�F("+x+","+y+")");
	}
	// �}�E�X�A�C�R�����������Ƃ��Ɏ��s����郁�\�b�h
	void mouseEntered(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("�}�E�X�A�C�R��������܂����B");
		System.out.println("\t���W�F("+x+","+y+")");
	}
	// �}�E�X�A�C�R�����o���Ƃ��Ɏ��s����郁�\�b�h
	void mouseExited(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("�}�E�X�A�C�R�����o�܂����B");
		System.out.println("\t���W�F("+x+","+y+")");
	}
	// �h���b�O���ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void mouseDragged(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		figure.draw(x,y);
	}
	// �}�E�X�A�C�R�����������Ƃ��Ɏ��s����郁�\�b�h
	void mouseMoved(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
	}
	// ���N���b�N�������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void mousePressed(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("���N���b�N��������܂����B");
		System.out.println("\t���W�F("+x+","+y+")");
		figure.draw(x,y);
	}
	// ���N���b�N�������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void mouseReleased(MouseEvent event){
		int x=(int)event.getX();
		int y=(int)event.getY();
		System.out.print("���N���b�N��������܂����B");
		System.out.println("\t���W�F("+x+","+y+")");
	}
	// �{�^���������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void buttonPressed(){
		System.out.println("�{�^����������܂����B");
		gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
	}
	// �X���C�_�[���N���b�N���ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void sliderClicked(){
		int value=(int)slider.getValue();
		System.out.print("�X���C�_�[���N���b�N����܂����B");
		System.out.println("\t�l�F"+value);
	}
	// �X���C�_�[�������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void sliderPressed(){
		int value=(int)slider.getValue();
		System.out.print("�X���C�_�[��������܂����B");
		System.out.println("\t\t�l�F"+value);
	}
	// �X���C�_�[�������ꂽ�Ƃ��Ɏ��s����郁�\�b�h
	void sliderReleased(){
		int value=(int)slider.getValue();
		System.out.print("�X���C�_�[��������܂����B");
		System.out.println("\t\t�l�F"+value);
	}
	// �`��}�`�̃N���X
	class Figure{
		// �}�`�̕`��
		void draw(int x,int y){
			// �}�`�̃T�C�Y����
			int width = 40;
			// �}�`�̐F����
			Color color = Color.BLACK;
			if(colorChoice.getValue()=="��"){
				color = Color.BLACK;
			}if(colorChoice.getValue()=="��"){
				color = Color.RED;
			}if(colorChoice.getValue()=="��"){	//�ǉ�
				color = Color.BLUE;
			}
			// �}�`�̕`��
			gc.setFill(color);
			if(shapeChoice.getValue()=="�~"){
				gc.fillOval(x-width/2,y-width/2,width,width);
			}if(shapeChoice.getValue()=="�l�p"){	//�ǉ�
				gc.fillRect(x-width/20,y-width/20,width,width);
			}
		}
	}
	// main���\�b�h
	public static void main(String args[]){
		launch();
	}
}
