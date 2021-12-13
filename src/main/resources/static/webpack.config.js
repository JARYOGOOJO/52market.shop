const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');
const dotenv = require("dotenv");
const webpack = require('webpack');

module.exports = (env) => {

    const {DEV} = env;
    if (DEV) {
        dotenv.config({path: './dev.env'})
    } else {
        dotenv.config({path: './.env'})
    }

    console.log("process.env.API_URL", process.env.API_URL)

    return {
        mode: 'development',
        entry: './src/index.js',
        output: {
            filename: 'bundle.js',
            path: path.resolve(__dirname, 'dist'),
            library: "app",
        },
        module: {
            rules: [
                {
                    test: /\.css$/i,
                    use: ['style-loader', 'css-loader'],
                }, {
                    test: /\.(png|svg|jpg|jpeg|gif)$/i,
                    type: 'asset/resource',
                },
            ],
        },
        devServer: {
            static: {
                directory: path.join(__dirname, 'dist'),
            },
            compress: true,
            port: 5500,
        },
        plugins: [
            new HtmlWebpackPlugin({
                template: path.resolve(__dirname, 'src/index.html'),
                filename: path.resolve(__dirname, 'index.html')
            }),
            new webpack.DefinePlugin({
                API_URL: JSON.stringify(process.env.API_URL)
            })
        ],
    }
};