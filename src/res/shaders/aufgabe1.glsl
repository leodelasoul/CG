#version 330

	
out  vec4 colorOut;
vec2 resolution = vec2( 700.0, 700.0 );
vec2 center ;
vec2 position = ( gl_FragCoord.xy) / resolution;


vec4 drawRect(in float size){
    colorOut = vec4(0.0, 1.0, 1.0, 1.0);

    if ((abs(position.x) < size ) && (abs(position.y) < size))
    
        return vec4( 1.0, 0.0, 0.0, 1.0 );
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
    
    if ((abs(rectPos.x) < 500.0 ) && (abs(rectPos.y) < 500.0))
    
         colorOut = vec4( 1.0, 0.0, 0.0, 1.0 );
         
    
}


void main(void)
{ 
	drawShapes(+150.0);
   
}