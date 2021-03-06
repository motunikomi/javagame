/*
 * Created on 2005/05/04
 *
 */

/**
 * Exclusive ORの学習
 * @author mori
 */
public class NNTest {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork();
        nn.init(2, 2, 1);
        nn.setLearningRate(0.2);
        
        // 訓練データの作成
        double[][] trainingSet = new double[4][3];
        // 訓練データ0
        trainingSet[0][0] = 0;  // 入力1
        trainingSet[0][1] = 0;  // 入力2
        trainingSet[0][2] = 0;  // 教師
        
        // 訓練データ1
        trainingSet[1][0] = 0;
        trainingSet[1][1] = 1;
        trainingSet[1][2] = 1;
        
        // 訓練データ2
        trainingSet[2][0] = 1;
        trainingSet[2][1] = 0;
        trainingSet[2][2] = 1;
        
        // 訓練データ3
        trainingSet[3][0] = 1;
        trainingSet[3][1] = 1;
        trainingSet[3][2] = 0;
        
        // 訓練データを学習
        double error = 1.0;
        int count = 0;
        while ((error > 0.0001) && (count < 50000)) {
            error = 0;
            count++;
            // 4つの訓練データを誤差が小さくなるまで繰り返し学習
            for (int i=0; i<4; i++) {
                // 入力層に値を設定
                nn.setInput(0, trainingSet[i][0]);
                nn.setInput(1, trainingSet[i][1]);
                // 教師信号を設定
                nn.setTeacherValue(0, trainingSet[i][2]);
                // 学習開始
                nn.feedForward();
                error += nn.calculateError();
                nn.backPropagate();
            }
            error /= 4.0;
            System.out.println(count + "\t" + error);
        }
        
        // 学習完了
        nn.setInput(0, 0);  // 入力1
        nn.setInput(1, 0);  // 入力2
        nn.feedForward();   // 出力を計算
        System.out.println(nn.getOutput(0));
    }
}
