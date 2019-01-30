package com.clug.nanal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.Random;

public class Survey5Activity extends AppCompatActivity {

    public enum Outer{
        CARDIGAN(21,2), ZIPUP(22,3), JACKET(23,4), LEATHER(24,5), COAT(25,7), PADDING(26,10);
        private int num;
        private int warm;

        Outer(int num, int warm){
            this.num=num;
            this.warm=warm;
        }
        int getNum(){
            return num;
        }
        int getWarm(){
            return warm;
        }
    }

    public enum Top{
        SLEEVELESS(11,0), SHORT_TSHIRT(12,0), SHORT_SHIRT(13,0), TSHIRT(14,1),
        SHIRT(15,1), POLAR(16,6), MTM(17,3), HOOD(18,3), KNIT(19,5);
        private int num;
        private int warm;

        Top(int num, int warm){
            this.num=num;
            this.warm=warm;
        }

        int getNum(){
            return num;
        }
        int getWarm(){
            return warm;
        }
    }

    public enum Bottom{
        SHORT_PANTS(1,0), PANTS(2,1), THICK_PANTS(3,3), STOCKING(4,1), SKIRT(5,0), LEGGINGS(6,2);
        private int num;
        private int warm;

        Bottom(int num, int warm){
            this.num=num;
            this.warm=warm;
        }

        int getNum(){
            return num;
        }
        int getWarm(){
            return warm;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey5);
    }

    int getRefTemp(int refTemp, int userTemp, int type){
        RadioButton soCold=(RadioButton) findViewById(R.id.soCold);
        RadioButton cold=(RadioButton) findViewById(R.id.cold);
        RadioButton soHot=(RadioButton) findViewById(R.id.soHot);
        RadioButton hot=(RadioButton) findViewById(R.id.hot);

        switch(type){
            case 1:{  //추위 잘 타는 사람
                if(soCold.isChecked()) userTemp+=4;
                else if(cold.isChecked()) userTemp+=2;
                else if(soHot.isChecked()) userTemp-=2;
                else if(hot.isChecked()) userTemp-=1; break;
            }
            case 2:{  //더위 잘 타는 사람
                if(soCold.isChecked()) userTemp+=2;
                else if(cold.isChecked()) userTemp+=1;
                else if(soHot.isChecked()) userTemp-=4;
                else if(hot.isChecked()) userTemp-=2; break;

            }
            case 3:{  //둘 다 잘 타는 사람
                if(soCold.isChecked()) userTemp+=2;
                else if(cold.isChecked()) userTemp+=1;
                else if(soHot.isChecked()) userTemp-=2;
                else if(hot.isChecked()) userTemp-=1; break;
            }
            case 4: {  //둘 다 잘 안타는 사람
                if(soCold.isChecked()) userTemp+=4;
                else if(cold.isChecked()) userTemp+=2;
                else if(soHot.isChecked()) userTemp-=4;
                else if(hot.isChecked()) userTemp-=2; break;
            }
        }
        return (refTemp/userTemp)/2;
    }

    public void onlyOneChecked(View v){
        RadioButton soCold=(RadioButton) findViewById(R.id.soCold);
        RadioButton cold=(RadioButton) findViewById(R.id.cold);
        RadioButton fine=(RadioButton) findViewById(R.id.fine);
        RadioButton hot=(RadioButton) findViewById(R.id.hot);
        RadioButton soHot=(RadioButton) findViewById(R.id.soHot);

        switch(v.getId()){
            case R.id.soCold:
                cold.setChecked(false);
                fine.setChecked(false);
                hot.setChecked(false);
                soHot.setChecked(false);
                break;
            case R.id.cold:
                soCold.setChecked(false);
                fine.setChecked(false);
                hot.setChecked(false);
                soHot.setChecked(false);
                break;
            case R.id.fine:
                soCold.setChecked(false);
                cold.setChecked(false);
                hot.setChecked(false);
                soHot.setChecked(false);
                break;
            case R.id.hot:
                soCold.setChecked(false);
                cold.setChecked(false);
                fine.setChecked(false);
                soHot.setChecked(false);
                break;
            case R.id.soHot:
                soCold.setChecked(false);
                cold.setChecked(false);
                fine.setChecked(false);
                hot.setChecked(false);
                break;
        }
    }
    public int[] selectClothes(float refTemp, float temp, int gender) { //무슨 옷 추천할지 고르는 함수
        int[] clothes = new int[6];
        float x = refTemp - temp;
        float min=10000;  //min==맞추려는 온도와 겉옷의 차이
        float remain=x;
        float prob = x*x;  //겉옷 입을지 말지에 대한 확률
        Random rand = new Random();
        if(x>=1){  //기준 온도보다 춥다
            if(rand.nextInt(100)<=prob){  //겉옷 입는다면
                clothes[0]=Outer.CARDIGAN.getNum();
                for(Outer outer:Outer.values()){
                    if(abs(x/2-outer.getWarm())<min){
                        min=abs(x/2-outer.getWarm());
                        remain=x-outer.getWarm();
                        clothes[0]=outer.getNum();
                    }
                }
                //하의 구하기
                remain/=4;
                System.arraycopy(combOfBottom(rounding(remain),gender), 0,
                        clothes,1,2);

                //상의 구하기
                remain*=3;
                System.arraycopy(combOfTop(rounding(remain),gender), 0,
                        clothes,3,3);
            }
            else {
                //겉옷 안 입는다면 5분의 1 하의
                remain=x/5;
                System.arraycopy(combOfBottom(rounding(remain),gender),0,
                        clothes,0,2);
                //나머지는 상의
                remain*=4;
                System.arraycopy(combOfTop(rounding(remain),gender),0,
                        clothes,2,3);
            }
        }
        else if(x<0){ //기준 온도보다 덥다면
            remain=abs(x/5);
            System.arraycopy(combOfBottom(rounding(remain),gender),0,
                    clothes,0,2);
            //나머지는 상의
            remain*=4;
            System.arraycopy(combOfTop(rounding(remain),gender),0,
                    clothes,2,3);
        }
        else { //기준 온도보다 0~1도 차이난다면
            int num=rand.nextInt(2);
            if(num==0) {
                System.arraycopy(combOfBottom(1,gender),0,clothes,0,1);
                System.arraycopy(combOfTop(0,gender),0,clothes,1,1);
            }
            else {
                System.arraycopy(combOfBottom(0,gender),0,clothes,0,1);
                System.arraycopy(combOfTop(1,gender),0,clothes,1,1);
            }
        }
        return sort(clothes);
    }

    public float abs(float num){
        if(num>=0) return num;
        else return -num;
    }

    public int rounding(float num){
        if(num-(int)num>=0.5){
            return (int)num+1;
        }
        else
            return (int)num;
    }

    public int[] sort(int[] arr){
        int temp;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]==0) arr[i]=100;
        }
        for(int i=0;i<arr.length;i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if(arr[j-1]>arr[j]) {
                    temp=arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        for(int i=0;i<arr.length;i++) {
            if(arr[i]==100) arr[i]=0;
        }
        return arr;
    }

    public int[] combOfBottom(int remain, int gender){
        int[] bottom=new int[2];
        Random rand=new Random();
        switch(remain){
            case 0: {
                if(gender==2){ //여자인 경우
                    if(rand.nextInt(2)==0){  //반반 확률로 치마 or 바지
                        bottom[0]=Bottom.SHORT_PANTS.getNum();
                    }
                    else{
                        bottom[0]=Bottom.SKIRT.getNum();
                    }
                }
                else{  //남자인 경우 바지만
                    bottom[0]=Bottom.SHORT_PANTS.getNum();
                }
                break;
            }

            case 1: {
                if(gender==2){ //여자인 경우
                    if(rand.nextInt(2)==0){  //반반 확률로 치마 or 바지
                        bottom[0]=Bottom.PANTS.getNum();
                    }
                    else{
                        bottom[0]=Bottom.STOCKING.getNum(); bottom[1]=Bottom.SKIRT.getNum();
                    }
                }
                else{  //남자인 경우
                    bottom[0]=Bottom.SHORT_PANTS.getNum();
                }
                break;
            }

            case 2:{
                if(gender==1) bottom[0]=Bottom.PANTS.getNum();
                else bottom[0]=Bottom.LEGGINGS.getNum();
                break;
            }

            default: {
                bottom[0] =Bottom.THICK_PANTS.getNum();
                break;
            }
        }
        return bottom;
    }

    public int[] combOfTop(int remain, int gender){
        int[] top=new int[3];
        Random rand=new Random();
        int num;
        switch(remain){
            case 0:{
                num=rand.nextInt(3);
                if(num==0){
                    if(gender==1) top[0]=Top.SHORT_TSHIRT.getNum();  //남자인 경우 티만
                    else top[0]=Top.SLEEVELESS.getNum();
                }
                else if(num==1){
                    top[0]=Top.SHORT_TSHIRT.getNum();
                }
                else{
                    top[0]=Top.SHORT_SHIRT.getNum();
                }
                break;
            }
            case 1:{
                num=rand.nextInt(2);
                if(num==0) top[0]=Top.TSHIRT.getNum();
                else top[0]=Top.SHIRT.getNum();
                break;
            }
            case 2:{
                top[0]=Top.TSHIRT.getNum();
                top[1]=Top.SHIRT.getNum();
                break;
            }
            case 3:{
                num=rand.nextInt(2);
                if(num==1) top[0]=Top.MTM.getNum();
                else top[0]=Top.HOOD.getNum();
                break;
            }
            case 4:{
                num=rand.nextInt(4);
                if(num==0){
                    top[0]=Top.TSHIRT.getNum();
                    top[1]=Top.MTM.getNum();
                }
                else if(num==1){
                    top[0]=Top.SHIRT.getNum();
                    top[1]=Top.MTM.getNum();
                }
                else if(num==2){
                    top[0]=Top.TSHIRT.getNum();
                    top[1]=Top.HOOD.getNum();
                }
                else{
                    top[0]=Top.SHIRT.getNum();
                    top[1]=Top.HOOD.getNum();
                }
                break;
            }
            case 5:{
                top[0]=Top.KNIT.getNum();
                break;
            }
            case 6:{
                num=rand.nextInt(2);
                if(num==0) top[0]= Top.POLAR.getNum();
                else{
                    top[0]= Top.SHIRT.getNum();
                    top[1]=Top.KNIT.getNum();
                }
                break;
            }
            case 7:{
                top[0]=Top.POLAR.getNum();
                top[1]=Top.SHIRT.getNum();
                break;
            }
            case 8:{
                top[0]=Top.TSHIRT.getNum();
                top[1]=Top.POLAR.getNum();
                top[2]=Top.SHIRT.getNum();
                break;
            }
            default:{
                top[0]=Top.POLAR.getNum();
                top[1]=Top.HOOD.getNum();
                break;
            }
        }
        return top;
    }
}