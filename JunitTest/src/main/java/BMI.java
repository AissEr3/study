public class BMI {
    // 定义属性
    private double weight;//体重
    private double height;//身高

    // Getter和Setter
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

    // 一次性设置体重和身高
    public void setParams(double w, double h){
        this.weight = w;
        this.height = h;
    }

    // 定义构造
    public BMI(double w, double h){
        this.weight = w;
        this.height = h;
    }

    public BMI(){
        this.weight = 0.0;
        this.height = 0.0;
    }

    // 定义功能方法：计算BMI，并判断所属分类
    public String getBMIType(){
        //1.初始化
        String result;
        double bmi = 0.0;
        // 如果体重和身高有效，则继续
        if(weight>0 && height>0){
            //2.计算BMI
            bmi = weight / (height*height);

            //3.判断所属分类
            if(bmi<18.5){
                result = "偏瘦";
            }else if(bmi<24){
                result = "正常";
            }else if(bmi<28){
                result = "偏胖";
            }else{
                result = "肥胖";
            }
        }else{
            result = "体重或身高数据错误！";
        }

        //4.返回分类
        return result;
    }

}
