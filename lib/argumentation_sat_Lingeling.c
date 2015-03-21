#include <jni.h>

#include "lingeling/code/lglib.h"
#include "argumentation_sat_Lingeling.h"

JNIEXPORT jlong JNICALL Java_argumentation_sat_Lingeling_lglinit(JNIEnv *env, jobject this){
	LGL* lgl = lglinit();
	return (jlong)lgl;
}

JNIEXPORT jlong JNICALL Java_argumentation_sat_Lingeling_lglclone(JNIEnv *env, jobject this, jlong lgl){
	LGL* lglPointer = (LGL*) lgl;
	return (jlong)lglclone(lglPointer);
}

JNIEXPORT void JNICALL Java_argumentation_sat_Lingeling_lglrelease(JNIEnv *env, jobject this, jlong lgl) {
	LGL* lglPointer = (LGL*) lgl;
	lglrelease(lglPointer);
	return;
}

JNIEXPORT jint JNICALL Java_argumentation_sat_Lingeling_lglderef(JNIEnv *env, jobject this, jlong lgl, jint number){
	LGL* lglPointer = (LGL*) lgl;
	return (jint)lglderef(lglPointer, (int)number);
}

JNIEXPORT void JNICALL Java_argumentation_sat_Lingeling_lgladd(JNIEnv *env, jobject this, jlong lgl, jint number) {
	LGL* lglPointer = (LGL*) lgl;
	lgladd(lglPointer, (int)number);
	return;
}

JNIEXPORT jint JNICALL Java_argumentation_sat_Lingeling_lglsat(JNIEnv *env, jobject this, jlong lgl){
	LGL* lglPointer = (LGL*) lgl;
	return (jint)lglsat(lglPointer);
}

JNIEXPORT void JNICALL Java_argumentation_sat_Lingeling_lglfreeze(JNIEnv *env, jobject this, jlong lgl, jint number){
	LGL* lglPointer = (LGL*) lgl;
	lglfreeze(lglPointer, (int)number);
	return;
}
