public class BMI {
    // ��������
    private double weight;//����
    private double height;//���

    // Getter��Setter
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // һ�����������غ����
    public void setParams(double w, double h){
        this.weight = w;
        this.height = h;
    }

    // ���幹��
    public BMI(double w, double h){
        this.weight = w;
        this.height = h;
    }

    public BMI(){
        this.weight = 0.0;
        this.height = 0.0;
    }

    // ���幦�ܷ���������BMI�����ж���������
    public String getBMIType(){
        //1.��ʼ��
        String result;
        double bmi = 0.0;
        // ������غ������Ч�������
        if(weight>0 && height>0){
            //2.����BMI
            bmi = weight / (height*height);

            //3.�ж���������
            if(bmi<18.5){
                result = "ƫ��";
            }else if(bmi<24){
                result = "����";
            }else if(bmi<28){
                result = "ƫ��";
            }else{
                result = "����";
            }
        }else{
            result = "���ػ�������ݴ���";
        }

        //4.���ط���
        return result;
    }

}
