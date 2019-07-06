const path = require("path");
const webpack = require("webpack");
const TerserPlugin = require('terser-webpack-plugin');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const RemovePlugin = require('remove-files-webpack-plugin');

module.exports = {
    mode: "production",
    entry: {
        // javascript
        "metrics.monitor": "./src/index.ts",
        "metrics.monitor.min": "./src/index.ts",
        // css
        "visualization": "./src/styles/visualization.scss",
        "heatmap": "./src/styles/heatmap.scss"
    },
    output: {
        path: path.resolve(__dirname, "dist"),
        filename: "[name].js",
        library: "MetricsMonitor",
        libraryTarget: "umd",
        globalObject: "this"
    },
    resolve: {
        extensions: [
            ".ts", ".js"
        ]
    },
    devtool: "source-map",
    optimization: {
        minimize: true,
        minimizer: [new TerserPlugin({
            include: /\.min\.js$/
        })]
    },
    plugins: [
        new CleanWebpackPlugin(),
        new CopyPlugin([
            {from: "src/styles", to: "styles/scss"}
        ]),
        new MiniCssExtractPlugin({
            filename: "styles/css/[name].css"
        }),
        new RemovePlugin({
            after: {
                include: [
                    "./dist/heatmap.js",
                    "./dist/visualization.js"
                ]
            }
        })
    ],
    module: {
        rules: [
            {
                test: /\.ts$/,
                use: "ts-loader",
                exclude: /node_modules/
            },
            {
                test: /\.scss$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {

                        }
                    },
                    "css-loader",
                    "sass-loader"
                ]
            }
        ]
    }
};
