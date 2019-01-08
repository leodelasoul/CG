#version 330
layout(location=0) in vec3 positions;
layout(location=1) in vec3 colors;

out vec3 vertexColor;

uniform mat4 matrix;
uniform mat4 projection_matrix;

void main(){

    vertexColor = colors;
    gl_Position = projection_matrix * matrix * vec4(positions, 1.0);
}