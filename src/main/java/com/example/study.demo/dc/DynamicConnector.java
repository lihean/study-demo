package com.example.study.demo.dc;

public class DynamicConnector {

    public class UF {
        private int[] id;
        private int count;

        public UF(int n) {
            for (int j = 0; j < n; j++) {
                id[j] = j;
            }

            count = n;
        }

        public int count() {
            return count;
        }

        public boolean connected(int p, int q) {
            return id[p] == id[q];
        }

        /**
         * 将p和q合并到同一个连通分量
         * @param p
         * @param q
         */
        public void union(int p, int q) {
            int pId = find(p);
            int qId = find(q);

            if (pId == qId) return;

            for (int i = 0; i < id.length; i++) {
                if (id[i] == pId) id[i] = qId;
            }

            count --;
        }

        public int find(int p) {
            return id[p];
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        DynamicConnector dynamicConnector = new DynamicConnector();
        UF uf = dynamicConnector.new UF(625);
    }
}
