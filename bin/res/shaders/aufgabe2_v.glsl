#version 330


layout(location=0) in vec2 eckenAusJava;
out vec3 zeug;
void main()
{ 
	zeug = vec3(1,0,0);
	gl_Position = vec4(eckenAusJava, 0.0, 1.0);
   
}