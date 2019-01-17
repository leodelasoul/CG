#version 330
layout(location=0) in vec3 positions;
layout(location=1) in vec3 colors;
layout(location=2) in vec3 color1;

out vec3 vertexColor;
out vec3 vertexColor1;

uniform mat4 matrix;
uniform mat4 projection_matrix;

void main(){

    vertexColor = colors;
    vertexColor1 = color1;
    gl_Position = projection_matrix * matrix * vec4(positions, 1.0);
}