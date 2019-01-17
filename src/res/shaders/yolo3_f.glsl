#version 330
out vec3 color;
in vec3 vertexColor1;

void main() {
	color = vertexColor1;
    //color = vec3(1.0,0.0,1.0);

}
