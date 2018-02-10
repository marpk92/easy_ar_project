package cn.easyar.samples.helloar;

import android.opengl.GLES20;
import android.renderscript.Matrix4f;

import java.util.Vector;

import cn.easyar.Vec2F;
import cn.easyar.samples.helloar.utils.CubeShaders;
import cn.easyar.samples.helloar.utils.SampleUtils;
import cn.easyar.samples.helloar.utils.Teapot;
import cn.easyar.samples.helloar.utils.Texture;

/**
 * Created by Marcin on 04.11.2017.
 */

public class TeapotRenderer {

    private int program_teapot;
    private int vertexHandle;
    private int textureCoordHandle;
    private int mvpMatrixHandle;
    private int texSampler2DHandle;

    private Teapot mTeapot;
    private boolean mModelIsLoaded = false;

    private Vector<Texture> mTextures;

    public void init(){

        for (Texture t : mTextures)
        {
            GLES20.glGenTextures(1, t.mTextureID, 0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, t.mTextureID[0]);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                    GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA,
                    t.mWidth, t.mHeight, 0, GLES20.GL_RGBA,
                    GLES20.GL_UNSIGNED_BYTE, t.mData);
        }

        program_teapot = SampleUtils.createProgramFromShaderSrc(CubeShaders.CUBE_MESH_VERTEX_SHADER, CubeShaders.CUBE_MESH_FRAGMENT_SHADER);

        vertexHandle = GLES20.glGetAttribLocation(program_teapot,
                "vertexPosition");
        textureCoordHandle = GLES20.glGetAttribLocation(program_teapot,
                "vertexTexCoord");
        mvpMatrixHandle = GLES20.glGetUniformLocation(program_teapot,
                "modelViewProjectionMatrix");
        texSampler2DHandle = GLES20.glGetUniformLocation(program_teapot,
                "texSampler2D");

        if(!mModelIsLoaded) {
            mTeapot = new Teapot();
            mModelIsLoaded = true;
        }
    }

    public void render(Matrix4f projectionMatrix, Matrix4f cameraview, Vec2F size){

    }
}
