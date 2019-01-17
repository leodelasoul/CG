package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;
	private ShaderProgram shaderProgram1;
	private ShaderProgram phongProgram;

    private float rotation = 0f;
    private Matrix4 transformationMatrix ;
    private Matrix4 transformationMatrix1;
    private Matrix4 projectionMatrix = new Matrix4(2f, 0f);
    private final float[] cPos = { -1f, 0f, 1f };
    private final float[] yPos = { 0f, 1f, 0f };
    private final float[] rPos = { 1f, 0f, 1f };
    private final float[] gPos = { 1f, 0f, -1f };
    private final float[] bPos = { -1f, 0f, -1f };

    private final float[] cColor = { 0f, 1f, 1f };
    private final float[] yColor = { 1f, 1f, 0f };
    private final float[] rColor = { 1f, 0f, 0f };
    private final float[] gColor = { 0f, 1f, 0f };
    private final float[] bColor = { 0f, 0f, 1f };

    private final float[] cryNormalVector = { 0f, 1f, 1f };
    private final float[] rgyNormalVector = { 1f, 1f, 0f };
    private final float[] bgyNormalVector = { 0f, 1f, -1f };
    private final float[] bcyNormalVector = { -1f, 1f, 0f };
    private final float[] crgbNormalVector = { 0f, -1f, 0f };

    private final float[][] normalVectors = {
            cryNormalVector, cryNormalVector, cryNormalVector,
            rgyNormalVector, rgyNormalVector, rgyNormalVector,
            bgyNormalVector, bgyNormalVector, bgyNormalVector,
            bcyNormalVector, bcyNormalVector, bcyNormalVector,
            crgbNormalVector, crgbNormalVector, crgbNormalVector,
            crgbNormalVector, crgbNormalVector, crgbNormalVector
    };
    private float[][] pixelCoords ={
            cPos, rPos, yPos,
            rPos, gPos, yPos,
            gPos, bPos, yPos,
            bPos, cPos, yPos,
            bPos, rPos, cPos,
            bPos, gPos, rPos
    };
    private float[][] colors1 = {
            rColor, gColor, bColor,
            cColor, rColor, bColor,
            yColor, yColor, bColor,
            yColor, bColor, gColor,
            yColor, gColor, rColor,
            yColor, rColor, cColor
    };

    private float[][] colors = {
            cColor, rColor, yColor,
            rColor, gColor, yColor,
            gColor, bColor, yColor,
            bColor, cColor, yColor,
            bColor, cColor, cColor,
            bColor, gColor, rColor
    };


    public static void main(String[] args) {
		new Aufgabe3undFolgende().start("CG Aufgabe 3", 1200, 800);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("aufgabe3");
        shaderProgram1 = new ShaderProgram("aufgabe3_v.glsl","yolo3_f.glsl",true); // pinl pyramid

        glUseProgram(shaderProgram.getId());
		int va = glGenVertexArrays();
		glBindVertexArray(va);
		int shaderNum = 0;
        this.bindShader(this.pixelCoords, 3, shaderNum++);
        this.bindShader(this.colors, 3, shaderNum++);
        this.bindShader(this.colors1, 3, shaderNum++);

        //int vab = glGenVertexArrays();
		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		glEnable(GL_CULL_FACE); // backface culling aktivieren
	}
    private void bindShader(float[][] arr, int groupSize, int pos) {
        float[] result = new float[3 * arr.length];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < groupSize; j++) {
                result[index++] = arr[i][j];
            }
        }
        this.bindShader(result, groupSize, pos);
    }

    private void bindShader(float[] arr, int groupSize, int pos) {
        int vb = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, vb);
        glBufferData(GL_ARRAY_BUFFER, arr, GL_STATIC_DRAW);
        glVertexAttribPointer(pos, groupSize, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(pos);
    }

	@Override
	public void update() {
		// Transformation durchfhren (Matrix anpassen)
        //Matrix4 mat = new Matrix4().translate(0f,0f,-6f);
        Matrix4 mat = new Matrix4().rotateY(rotation * .3f).rotateZ(rotation * 0.3f).translate(-1.5f,0f,-6f);
        Matrix4 mat1 = new Matrix4().rotateY(rotation * .3f).rotateZ(rotation * 0.3f).translate(-1.5f,0f,-6f);

        rotation += 0.03f;
        //this.transformationMatrix = mat;
        this.transformationMatrix1 = mat1;
        this.transformationMatrix = mat;


    }

	@Override
	protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glUseProgram(this.shaderProgram.getId());
        this.passMatrix("matrix", this.transformationMatrix);
        this.passMatrix("projection_matrix", this.projectionMatrix);
        glDrawArrays(GL_TRIANGLES, 0, this.pixelCoords.length);
        // Matrix an Shader bertragen
		// VAOs zeichnen
        glUseProgram(this.shaderProgram1.getId());
        this.passMatrix("matrix", new Matrix4(this.transformationMatrix1).translate(3f,0,0));
        this.passMatrix("projection_matrix",this.projectionMatrix);
        glDrawArrays(GL_TRIANGLES, 0, this.pixelCoords.length);



    }

	@Override
	public void destroy() {

	}


    private void passMatrix(String name, Matrix4 m) {
        int loc = glGetUniformLocation(this.shaderProgram.getId(), name);
        glUniformMatrix4fv(loc, false, m.getValuesAsArray());
    }
}
