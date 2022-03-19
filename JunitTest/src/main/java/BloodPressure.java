public class BloodPressure {
    int systolicPressure;// ����ѹ
    int diatolicPressure;// ����ѹ

    // Getter and Setter
    public int getSystolicPressure() {
        return systolicPressure;
    }
    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }
    public int getDiatolicPressure() {
        return diatolicPressure;
    }
    public void setDiatolicPressure(int diatolicPressure) {
        this.diatolicPressure = diatolicPressure;
    }
    // ��������ѹ������ѹ
    public void setParams(int sysPressure, int diaPressure){
        systolicPressure = sysPressure;
        diatolicPressure = diaPressure;
    }

    // Construction
    public BloodPressure(){
        systolicPressure = 0;
        diatolicPressure = 0;
    }
    public BloodPressure(int sysPressure, int diaPressure){
        systolicPressure = sysPressure;
        diatolicPressure = diaPressure;
    }

    // ����Ѫѹֵ�ж�����Ѫѹ�ּ�
    public String getPressureLevel(){
        String level;
        if(systolicPressure<120 && diatolicPressure<80){
            level = "����";
        }else if( (systolicPressure>=120 && systolicPressure<=139) && (diatolicPressure>=80 && diatolicPressure<=89)){
            level = "������ֵ";
        }else if( (systolicPressure>=140 && systolicPressure<=159) || (diatolicPressure>=90 && diatolicPressure<=99)){
            level = "1����Ѫѹ";
        }else if( (systolicPressure>=160 && systolicPressure<=179) || (diatolicPressure>=100 && diatolicPressure<=109)){
            level = "2����Ѫѹ";
        }else if( (systolicPressure>=180) || (diatolicPressure>=110)){
            level = "3����Ѫѹ";
        }else{
            level = "�쳣ֵ";
        }
        return level;
    }
}
