#version 330
out vec3 color;
in vec3 vertexColor;

void main() {
	color = vertexColor;

    //gl_FragColor = vec4(1.0,0.0,1.0,1.0);
}
