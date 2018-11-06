#version 330
#define PI 3.14159265359
	
out  vec4 colorOut;
vec2 resolution = vec2( 700.0, 700.0 );
vec2 center ;
vec2 position = gl_FragCoord.xy/resolution.xy;
uniform vec2 mouse; 
uniform vec2 u_time; 
uniform float u_angle;

vec4 drawRect(in vec2 rota){
    
    vec2 position = ( gl_FragCoord.xy / resolution.xy ) + mouse / 4.0;

                   // x1  y1   x2   y2
    vec4 rect = vec4(0.2, 0.3, 0.4, 0.5);
    vec2 hv = step(rota, position) * step(position, rota);
    float onOff = hv.x * hv.y;

    return mix(vec4(0,1,1,0), vec4(1,0,0,0), onOff);
    //return vec4(1,0,0,0);
}



void drawShapes(in float pos){
	
    vec2 center = vec2(resolution.x/2.0,resolution.y/2.0); 
    vec2 rectPos = vec2(resolution.x/2.0+pos,resolution.y/2.0+pos);
    vec2 loc = gl_FragCoord.xy;
	
    float radius=distance(loc,(center+pos));
	
    if (radius < 100.0)
        colorOut = vec4(1,0,0,1); // red
    else
        colorOut = vec4(1,1,1,1); // white
    	
    
}

void main(void)
{ 
	//drawShapes(+150.0);
	
    float sin_factor = sin(u_angle);
    float cos_factor = cos(u_angle);
    position = (position - 0.5) * mat2(cos_factor, sin_factor, -sin_factor, cos_factor);
    position += 0.5;

	colorOut = drawRect(position);
   
}