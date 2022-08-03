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
import javafx.scene.shape.ArcType;

// ��Q�����}�X����ړ�����Q�[��
public class Sample3 extends Application{
	// �L�����o�X�̐ݒ�
	Canvas canvas = new Canvas(800,600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	// �Q�����}�X�̐ݒ�
	int width=50;					// �}�X�̕��̒���
	Tile[][] tile = new Tile[8][8];	// �e8�~8�}�X�̍쐬
	// �v���C���[�i��j�̍쐬
	Player player = new Player();
	// start���\�b�h
	public void start(Stage stage){
		// �e�}�X�̍쐬
		for(int i=0;i<tile.length;i++){
			for(int j=0;j<tile[i].length;j++){
				tile[i][j] = new Tile(i,j,false,false);
			}
		}
		//hasWall
		tile[7][0].hasWall = true;
		tile[7][1].hasWall = true;
		tile[6][2].hasWall = true;
		tile[6][3].hasWall = true;
		tile[6][4].hasWall = true;
		tile[6][5].hasWall = true;
		tile[5][4].hasWall = true;
		tile[4][4].hasWall = true;
		tile[4][5].hasWall = true;
		tile[4][7].hasWall = true;
		tile[3][3].hasWall = true;
		tile[3][0].hasWall = true;
		tile[3][1].hasWall = true;
		tile[2][3].hasWall = true;
		tile[2][6].hasWall = true;
		tile[2][7].hasWall = true;
		tile[0][3].hasWall = true;
		tile[0][2].hasWall = true;
		//hasFood
		tile[0][6].hasFood = true;
		tile[0][7].hasFood = true;
		tile[1][5].hasFood = true;
		tile[1][6].hasFood = true;
		tile[2][1].hasFood = true;
		tile[2][2].hasFood = true;
		tile[3][6].hasFood = true;
		tile[4][0].hasFood = true;
		tile[5][5].hasFood = true;
		tile[7][7].hasFood = true;
		tile[4][0].hasFood = true;
		tile[5][0].hasFood = true;
		tile[6][0].hasFood = true;
		tile[4][1].hasFood = true;
		tile[5][1].hasFood = true;
		tile[6][1].hasFood = true;
		tile[7][2].hasFood = true;
		tile[7][4].hasFood = true;
		drawAll();
		
		// �������̕`��
		gc.setFill(Color.BLACK);
		gc.fillText("���L�[�F�ړ��i�����͖������j",400,100);
		
		// pane1�̍쐬�i���g�p�A���R�Ɏg���Ă��������j
		FlowPane pane1 = new FlowPane();
		
		// pane�ƃL�����o�X�����[�g�y�C���֔z�u
		VBox pane = new VBox();
		pane.getChildren().add(pane1);
		pane.getChildren().add(canvas);
		
		// �L�[�̃C�x���g�n���h���̓o�^
		Scene scene = new Scene(pane);
		scene.setOnKeyTyped(event -> keyTyped(event));
		scene.setOnKeyPressed(event -> keyPressed(event));
		scene.setOnKeyReleased(event -> keyReleased(event));
		
		// �E�B���h�E�̕\����
		stage.setScene(scene);
		stage.setTitle("�Q�����}�X���ړ�����Q�[��");//�^�C�g���̐ݒ�
		stage.sizeToScene();
		stage.show();
	}
	// �������^�C�v���ꂽ����s����郁�\�b�h
	void keyTyped(KeyEvent event){
		String text = event.getCharacter();
		System.out.print("�^�C�v����܂����B");
		System.out.println("\tString�F\t"+text);
	}
	// �L�[�������ꂽ����s����郁�\�b�h
	void keyPressed(KeyEvent event){
		KeyCode key = event.getCode();
		System.out.print("�L�[��������܂����B");
		System.out.println("\tKeyCode�F\t"+key);
		// �����ꂽ���L�[�ɉ����ċ�̈ړ�������̕ύX
		if(key==KeyCode.RIGHT){
			player.moveTo(player.x+1,player.y);
			player.direction=1;
		}else if(key==KeyCode.LEFT){
			player.moveTo(player.x-1,player.y);
			player.direction=3;
		}else if(key==KeyCode.UP){
			player.moveTo(player.x,player.y-1);
			player.direction=2;
		}else if(key==KeyCode.DOWN){
			player.moveTo(player.x,player.y+1);
			player.direction=4;
		}
		drawAll();	
	}
	// �L�[�������ꂽ����s����郁�\�b�h
	void keyReleased(KeyEvent event){
		KeyCode key = event.getCode();
		System.out.print("�L�[��������܂����B");
		System.out.println("\tKeyCode�F\t"+key);
	}
	// �����̑����\�b�h
	// ��Ƃ��ׂẴ}�X�̕`��
	void drawAll(){
		for(int i=0;i<tile.length;i++){
			for(int j=0;j<tile[i].length;j++){
				tile[i][j].draw();
			}
		}
		player.draw();
	}
	// �}�X�̃N���X
	class Tile{
		int x,y;			// �}�X�̔z�u�ꏊ
		boolean hasWall;	// �ǂ��z�u����Ă��邩�̐^�U�l
		boolean hasFood;	// �a���z�u����Ă��邩�̐^�U�l
		Tile(int x,int y,boolean b1,boolean b2){
			this.x = x;
			this.y = y;
			this.hasWall = b1;
			this.hasFood = b2;
		}
		// �}�X�̕`��
		void draw(){
			gc.setFill(Color.BLACK);
			gc.fillRect(x*width,y*width,width,width);
			// �ǂ�a���z�u����Ă�����ǉ��`��
			if(hasWall){
				gc.setFill(Color.CYAN);
				gc.fillRect(x*width,y*width,width,width);
			}else if(hasFood){
				gc.setFill(Color.ORANGE);
				gc.fillRect(x*width+width/3,y*width+width/3,width/3,width/3);
			}
		}
	}
	// �v���C���[�i��j�̃N���X
	class Player{
		int x=0;
		int y=0;
		int direction=1; //1���E�A2����A3�����A4���� �Ƃ���B
		// ��̈ړ��i���W�ύX�j
		void moveTo(int x,int y){
			// �ړ���ɕǂ�a�����邩�ǂ����ŏꍇ����
			if(!tile[x][y].hasWall){
				this.x = x;
				this.y = y;
			}
			if(tile[x][y].hasFood){
				tile[x][y].hasFood=false;
			}
		}
		// ��̕`��
		void draw(){
			gc.setFill(Color.YELLOW);
			// �����Ă�������ɂ���ĕ`�悷����e��ύX
			if(direction==1){
				gc.fillArc(x*width,y*width,width,width,30,300,ArcType.ROUND);
			}else if(direction==3){
				gc.fillArc(x*width,y*width,width,width,210,300,ArcType.ROUND);
			}else if(direction==2){//UP
				gc.fillArc(x*width,y*width,width,width,120,300,ArcType.ROUND);
			}else if(direction==4){//DOWN
				gc.fillArc(x*width,y*width,width,width,300,300,ArcType.ROUND);
			}
		}
	}
	// main���\�b�h
	public static void main(String args[]){
		launch();
	}
}
