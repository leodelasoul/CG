package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		// folgende Zeile ld automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());
		float [] vertex_data = {
				-0.5f,-0.5f,
				0.5f,-0.5f,
				0.0f,0.5f,
		};
		float[] frag_data = { 1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f };
		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);
		int vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER,vertex_data, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(0);
		int vboId2 = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId2);
		glBufferData(GL_ARRAY_BUFFER,frag_data, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Zeichenflche leeren
		glDrawArrays(GL_TRIANGLES, 0, 6);
		// hier vorher erzeugte VAOs zeichnen
	}

	@Override
	public void destroy() {
	}
}
