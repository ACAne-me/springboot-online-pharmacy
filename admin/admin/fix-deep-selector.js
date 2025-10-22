const fs = require('fs');
const path = require('path');

// 替换所有.vue文件中的/deep/选择器为::v-deep
function replaceDeepSelector(directory) {
    fs.readdir(directory, (err, files) => {
        if (err) {
            console.error('读取目录失败:', err);
            return;
        }

        files.forEach(file => {
            const filePath = path.join(directory, file);

            fs.stat(filePath, (err, stats) => {
                if (err) {
                    console.error('获取文件状态失败:', err);
                    return;
                }

                if (stats.isDirectory()) {
                    // 递归处理子目录
                    replaceDeepSelector(filePath);
                } else if (path.extname(file) === '.vue') {
                    // 读取并替换.vue文件内容
                    fs.readFile(filePath, 'utf8', (err, data) => {
                        if (err) {
                            console.error('读取文件失败:', filePath, err);
                            return;
                        }

                        // 替换/deep/为::v-deep
                        const newData = data.replace(/\/deep\//g, '::v-deep');

                        // 只有当内容有变化时才写入文件
                        if (newData !== data) {
                            fs.writeFile(filePath, newData, 'utf8', (err) => {
                                if (err) {
                                    console.error('写入文件失败:', filePath, err);
                                } else {
                                    console.log('已成功修复:', filePath);
                                }
                            });
                        }
                    });
                }
            });
        });
    });
}

// 开始替换操作
const srcDir = path.join(__dirname, 'src');
console.log('开始修复所有.vue文件中的/deep/选择器...');
replaceDeepSelector(srcDir);