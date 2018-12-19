/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : api_doc

 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 12/19/2018 09:40:09 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Records of `catalog`
-- ----------------------------
BEGIN;
INSERT INTO `catalog` VALUES ('70', '一级目录1', '99', '68', null, '0', '2018-11-26 17:03:58'), ('71', '一级目录二', '99', '68', null, '0', '2018-11-26 17:04:08'), ('72', '二级目录11', '99', null, '70', '0', '2018-11-26 17:04:30'), ('75', '一级目录3', '99', '68', null, '0', '2018-11-28 11:35:51'), ('76', '一级目录4', '99', '68', null, '0', '2018-11-28 11:35:56'), ('77', '一级目录5', '99', '68', null, '0', '2018-11-28 11:36:01'), ('78', '一级目录6', '99', '68', null, '1', '2018-11-28 11:36:05'), ('79', '一级目录7', '99', '68', null, '1', '2018-11-28 11:36:08'), ('80', '一级目录8', '99', '68', null, '1', '2018-11-28 11:36:17'), ('81', '一级目录9', '99', '68', null, '1', '2018-11-28 11:36:40'), ('82', '一级目录10', '99', '68', null, '1', '2018-11-28 11:36:46'), ('83', '一级目录11', '99', '68', null, '1', '2018-11-28 11:36:51'), ('84', '一级目录12', '99', '68', null, '1', '2018-11-28 11:36:55'), ('86', '技术规范', '0', '69', null, '0', '2018-12-04 18:20:00'), ('87', '任务计划', '1', '69', null, '0', '2018-12-04 18:20:19'), ('88', '会议记录', '2', '69', null, '0', '2018-12-04 18:20:26'), ('89', '工具教程', '3', '69', null, '0', '2018-12-04 18:20:38');
COMMIT;

-- ----------------------------
--  Records of `item`
-- ----------------------------
BEGIN;
INSERT INTO `item` VALUES ('66', '2018-11-26 17:00:48', '0', '单页项目', '单页项目1', '2', '2018-11-26 17:00:48', '12345', '0'), ('68', '2018-11-26 17:01:30', '0', '', '常规项目1', '1', '2018-11-26 17:01:30', '', '0'), ('69', null, '0', '用于开放平台首页展示', '开放API', '0', null, null, '0'), ('94', '2018-12-05 14:40:59', '1', 'ttttt', 'tt', '2', '2018-12-05 14:40:59', null, '0'), ('96', '2018-12-05 14:41:28', '0', 'ccc', 'aaa', '2', '2018-12-05 14:41:28', null, '1');
COMMIT;

-- ----------------------------
--  Records of `page`
-- ----------------------------
BEGIN;
INSERT INTO `page` VALUES ('67', null, '99', null, '66', '2018-11-26 17:00:48', '0', null, '单页项目1'), ('69', null, '99', null, '68', null, '0', 0x23232320e5ae89e585a8e8a784e88c830a2d20e8b4a6e58fb70a0a20e99d9ee5bf85e8a681e68385e586b5e4b88befbc8ce4bba5e99d9e726f6f74e794a8e688b7e799bbe5bd95e6938de4bd9c0a20e5a49ae4babae6938de4bd9ce78eafe5a283efbc8ce7bb99e5ae9ae699aee9809ae794a8e688b7e8b4a6e58fb70a0a2d2020e58fa3e4bba40a0a20e68ea7e588b6e58fa3e4bba4e5bcbae5baa6efbc8ce4bfaee694b9e9a291e6aca1e4b8bae4b880e4b8aae69c88e68896e4b889e4b8aae69c880a0a2d2020e7a381e79b98e4bdbfe794a80a0a20e79b91e68ea7e7a381e79b98e4bdbfe794a8e68385e586b5efbc8ce998b2e6ada2e78886e79b980a0a2d2020e9878de8a681e69687e4bbb6e7aea1e790860a20e5a487e4bbbd0a20e6b7bbe58aa0e4b88de58fafe69bb4e694b9e4bd8defbc8ce8bf99e6a0b7726f6f74e794a8e688b7e4bfaee694b9e4b99fe8a681e68c87e5ae9ae789b9e5ae9ae58f82e695b00a200a206060600a0963686174747220202b69202066696c656e616d65202020202f2a20e58aa0e28098e4b88de58fafe69bb4e694b9e4bd8de28098202a2f2020202020202020200a0963686174747220202d69202066696c656e616d65202020202f2a20e58ebbe68e89e28098e4b88de58fafe69bb4e694b9e4bd8de28098202a2f0a6060600a0a0a0a2d20204950efbc8ce7abafe58fa30a0a20e99990e588b6e7abafe58fa3e8aebfe997aee69cbae599a80a20e7a681e6ada2e4bfaee694b9e799bbe5bd95e7abafe58fa30a20e7a681e6ada2e7bb91e5ae9ae4bbbbe6848fe7a88be5ba8fe588b0e789b9e5ae9ae7abafe58fa30a20e8bf9be7a88be4b88de79bb4e68ea5e7bb91e5ae9ae59ca8e78eafe8b7afe59cb0e59d80e4b88ae99da228302e302e302e3029efbc8ce99c80e7bb91e5ae9ae58685e7bd9169700a0a2d2020e79b91e68ea70a0a20e5bc80e590afe79b91e68ea720e6af94e5a682e79b91e5bc80e590afe68ea7e697a5e5bf97efbc8c6e67696e78efbc8c7468696e6b6a73e9bb98e8aea4e887aae5b8a6e5bc80e590afe697a5e5bf970a20e78988e69cac0a20e7b3bbe7bb9fe68896e8bdafe4bbb6e4bb8ee5ae98e696b9e6b8a0e98193e4b88be8bdbd0a20e7b3bbe7bb9fe591bde4bba4e7a681e6ada2e4bfaee694b9efbc8ce5a682726defbc8c6d760a200a2d2020e8aebfe997aee68ea7e588b60a0a20e7a681e794a8e79baee5bd95e6b58fe8a788efbc8ce6958fe6849fe69687e4bbb6e695b0e68daee7a681e6ada2e694bee59ca8776562e79baee5bd95e4b88be99da2efbc88e58c85e68bac73766eefbc8c637673e78988e69cace68ea7e588b6e79a84e69687e4bbb6efbc890a0a23232320e6938de4bd9ce8a784e88c830a0a2d2020e69c8de58aa1e599a80a0a20e58db1e999a9e6938de4bd9ce5898de4b880e5ae9ae8a681e5a487e4bbbd0a0a2d2020e695b0e68daee5ba930a0a20e7bb91e5ae9ae58685e7bd9169700a20e8aebee7bdaee799bbe5bd95e5af86e7a0810a20e588a0e999a4e9bb98e8aea4e695b0e68daee5ba93e58f8ae794a8e688b70a20e696b0e5bbba6d7973716ce794a8e688b7e5928ce7bb84efbc8ce79bb8e585b3e69d83e99990e7bb99e588b06d7973716ce794a8e688b70a20e5ba93efbc8ce8a1a8e7baa7e588abe6938de4bd9ce6858ee9878d0a0a2d2020e58db1e999a9e6938de4bd9c0a0a2060726d202d7266206469722f66696c656e616d656020e6ada4e591bde4bba4e5bcbae588b6e588a0e999a4e69687e4bbb6e68896e79baee5bd95efbc8ce6938de4bd9ce5898de58aa1e5bf85e5a487e4bbbdefbc886370202d72efbc890a20606b696c6c202d39207069646020e6ada4e591bde4bba4e5bcbae588b6e69d80e68e89e6ada3e59ca8e8bf90e8a18ce79a84e8bf9be7a88befbc8ce4bdbfe5bd93e5898de8bf9be7a88be5afb9e5ba94e7a88be5ba8fe7ab8be58db3e98080e587baefbc8ce4b880e888ace4bc9ae4b8a2e5a4b1e695b0e68dae0a20e695b0e68daee5ba93e79bb8e585b320efbc8864656c657465efbc8c64726f70efbc8c7472756e63617465efbc8920e6938de4bd9ce5898de4b880e5ae9ae5a487e4bbbde695b0e68daeefbc8ce6938de4bd9ce697b6e69c80e5a5bde69c89e4babae7bb93e5afb90a2d2020e887aae58aa8e58c960a0a20e9878de8a681e69687e4bbb6e68896e695b0e68daee5ba93e5a487e4bbbde887aae58aa8e58c9620e58fafe9809ae8bf8763726f6e746162e4bbbbe58aa1e69da5e5ae9ae697b6e689a7e8a18c0a20e69c8de58aa1e68896e7a88be5ba8fe69cace8baabe5b19ee4ba8ee5ae9ae697b6e4bbbbe58aa1efbc8ce58899e5afb9e4ba8ee9878de8a681e695b0e68daee38081e69687e4bbb6e59ca8e69c8de58aa1e68896e7a88be5ba8fe58685e983a8e887aae8a18ce5ae9ee78eb0, '页面1'), ('73', null, '99', '72', null, null, '0', 0xe6aca2e8bf8ee4bdbfe794a853686f77446f63efbc81, '页面3'), ('74', null, '99', '70', null, null, '0', 0xe6aca2e8bf8ee4bdbfe794a853686f77446f63efbc81, '页面2'), ('85', null, '99', null, '69', null, '0', 0xe8bf99e698afe794b1e7b3bbe7bb9fe7949fe68890e79a84e68a80e69cafe59ba2e9989fe69687e6a1a3e7a4bae4be8be9a1b9e79baee380820a0ae794b1e4ba8ee6af8fe4b8aae59ba2e9989fe983bde4b88de5a4aae4b880e6a0b7efbc8ce69585e7949fe68890e79a84e69687e6a1a3e4bb85e4be9be58f82e88083efbc8ce4b88de5bf85e68b98e6b3a5e4ba8ee58faae4bdbfe794a8e8bf99e7a78de6a8a1e69dbfe69da5e5ad98e694bee59ba2e9989fe69687e6a1a3e8b584e696990a0a687474703a2f2f6c6f63616c686f73743a383038312f232f36383f706167655f69643d3639, '说明'), ('90', null, '99', '86', null, null, '0', 0x23232320e5ae89e585a8e8a784e88c830a2d20e8b4a6e58fb70a0a20e99d9ee5bf85e8a681e68385e586b5e4b88befbc8ce4bba5e99d9e726f6f74e794a8e688b7e799bbe5bd95e6938de4bd9c0a20e5a49ae4babae6938de4bd9ce78eafe5a283efbc8ce7bb99e5ae9ae699aee9809ae794a8e688b7e8b4a6e58fb70a0a2d2020e58fa3e4bba40a0a20e68ea7e588b6e58fa3e4bba4e5bcbae5baa6efbc8ce4bfaee694b9e9a291e6aca1e4b8bae4b880e4b8aae69c88e68896e4b889e4b8aae69c880a0a2d2020e7a381e79b98e4bdbfe794a80a0a20e79b91e68ea7e7a381e79b98e4bdbfe794a8e68385e586b5efbc8ce998b2e6ada2e78886e79b980a0a2d2020e9878de8a681e69687e4bbb6e7aea1e790860a20e5a487e4bbbd0a20e6b7bbe58aa0e4b88de58fafe69bb4e694b9e4bd8defbc8ce8bf99e6a0b7726f6f74e794a8e688b7e4bfaee694b9e4b99fe8a681e68c87e5ae9ae789b9e5ae9ae58f82e695b00a200a206060600a0963686174747220202b69202066696c656e616d65202020202f2a20e58aa0e28098e4b88de58fafe69bb4e694b9e4bd8de28098202a2f2020202020202020200a0963686174747220202d69202066696c656e616d65202020202f2a20e58ebbe68e89e28098e4b88de58fafe69bb4e694b9e4bd8de28098202a2f0a6060600a0a0a0a2d20204950efbc8ce7abafe58fa30a0a20e99990e588b6e7abafe58fa3e8aebfe997aee69cbae599a80a20e7a681e6ada2e4bfaee694b9e799bbe5bd95e7abafe58fa30a20e7a681e6ada2e7bb91e5ae9ae4bbbbe6848fe7a88be5ba8fe588b0e789b9e5ae9ae7abafe58fa30a20e8bf9be7a88be4b88de79bb4e68ea5e7bb91e5ae9ae59ca8e78eafe8b7afe59cb0e59d80e4b88ae99da228302e302e302e3029efbc8ce99c80e7bb91e5ae9ae58685e7bd9169700a0a2d2020e79b91e68ea70a0a20e5bc80e590afe79b91e68ea720e6af94e5a682e79b91e5bc80e590afe68ea7e697a5e5bf97efbc8c6e67696e78efbc8c7468696e6b6a73e9bb98e8aea4e887aae5b8a6e5bc80e590afe697a5e5bf970a20e78988e69cac0a20e7b3bbe7bb9fe68896e8bdafe4bbb6e4bb8ee5ae98e696b9e6b8a0e98193e4b88be8bdbd0a20e7b3bbe7bb9fe591bde4bba4e7a681e6ada2e4bfaee694b9efbc8ce5a682726defbc8c6d760a200a2d2020e8aebfe997aee68ea7e588b60a0a20e7a681e794a8e79baee5bd95e6b58fe8a788efbc8ce6958fe6849fe69687e4bbb6e695b0e68daee7a681e6ada2e694bee59ca8776562e79baee5bd95e4b88be99da2efbc88e58c85e68bac73766eefbc8c637673e78988e69cace68ea7e588b6e79a84e69687e4bbb6efbc890a0a23232320e6938de4bd9ce8a784e88c830a0a2d2020e69c8de58aa1e599a80a0a20e58db1e999a9e6938de4bd9ce5898de4b880e5ae9ae8a681e5a487e4bbbd0a0a2d2020e695b0e68daee5ba930a0a20e7bb91e5ae9ae58685e7bd9169700a20e8aebee7bdaee799bbe5bd95e5af86e7a0810a20e588a0e999a4e9bb98e8aea4e695b0e68daee5ba93e58f8ae794a8e688b70a20e696b0e5bbba6d7973716ce794a8e688b7e5928ce7bb84efbc8ce79bb8e585b3e69d83e99990e7bb99e588b06d7973716ce794a8e688b70a20e5ba93efbc8ce8a1a8e7baa7e588abe6938de4bd9ce6858ee9878d0a0a2d2020e58db1e999a9e6938de4bd9c0a0a2060726d202d7266206469722f66696c656e616d656020e6ada4e591bde4bba4e5bcbae588b6e588a0e999a4e69687e4bbb6e68896e79baee5bd95efbc8ce6938de4bd9ce5898de58aa1e5bf85e5a487e4bbbdefbc886370202d72efbc890a20606b696c6c202d39207069646020e6ada4e591bde4bba4e5bcbae588b6e69d80e68e89e6ada3e59ca8e8bf90e8a18ce79a84e8bf9be7a88befbc8ce4bdbfe5bd93e5898de8bf9be7a88be5afb9e5ba94e7a88be5ba8fe7ab8be58db3e98080e587baefbc8ce4b880e888ace4bc9ae4b8a2e5a4b1e695b0e68dae0a20e695b0e68daee5ba93e79bb8e585b320efbc8864656c657465efbc8c64726f70efbc8c7472756e63617465efbc8920e6938de4bd9ce5898de4b880e5ae9ae5a487e4bbbde695b0e68daeefbc8ce6938de4bd9ce697b6e69c80e5a5bde69c89e4babae7bb93e5afb90a2d2020e887aae58aa8e58c960a0a20e9878de8a681e69687e4bbb6e68896e695b0e68daee5ba93e5a487e4bbbde887aae58aa8e58c9620e58fafe9809ae8bf8763726f6e746162e4bbbbe58aa1e69da5e5ae9ae697b6e689a7e8a18c0a20e69c8de58aa1e68896e7a88be5ba8fe69cace8baabe5b19ee4ba8ee5ae9ae697b6e4bbbbe58aa1efbc8ce58899e5afb9e4ba8ee9878de8a681e695b0e68daee38081e69687e4bbb6e59ca8e69c8de58aa1e68896e7a88be5ba8fe58685e983a8e887aae8a18ce5ae9ee78eb0, '服务器操作规范'), ('91', null, '99', '87', null, null, '0', 0x23232320e7acac31e591a80a0a202a2a32303232e5b9b435e69c8831e697a5e887b332303232e5b9b435e69c8835e697a52a2a0a0a7c20e4bbbbe58aa1e58685e5aeb920207c2020e4babae591982020207c20e9a284e4bcb0e88097e697b620207c20e5ae9ee99985e88097e697b620207c20e5a487e6b3a820207c0a7c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c0a7ce5a29ee58aa0e8a1a8e6a0bce4bfa1e681afe5afbce587bae58a9fe883bd20207c20e5bca0e4b88920207c202031e5a4a9207c2020207c2020207c0a7ce7a6bbe7babfe4bbbbe58aa1e79a84e7958ce99da2e5bc80e58f9120207c20e5bca0e4b88920207c202032e5a4a9207c2020207c2020207c0a7c20e7ae97e6b395e8b083e695b4efbc8ce58aa0e585a5e58285e9878ce58fb6e58f98e68da2e7ae97e6b395e4bc98e58c9620207c20e69d8ee59b9b20207c2033e5a4a97c202020207c202020207c2020200a7c20e5aeb9e781bee5a487e4bbbde696b9e6a188e79a84e5ae9ee696bd20207c20e78e8be4ba9420207c2034e5a4a97c202020207c202020207c2020200a7c20e69fa5e8afa2e68ea5e58fa3e79a84e695b4e79086e5928ce4bc98e58c9620207c20e8b5b5e585ad20207c2034e5a4a97c202020207c202020207c20207c200a0a23232320e7acac32e591a80a0a202a2a32303232e5b9b435e69c8838e697a5e887b332303232e5b9b435e69c883133e697a52a2a0a0a7c20e4bbbbe58aa1e58685e5aeb920207c2020e4babae591982020207c20e9a284e4bcb0e88097e697b620207c20e5ae9ee99985e88097e697b620207c20e5a487e6b3a820207c0a7c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d207c0a7ce5a29ee58aa0e8a1a8e6a0bce4bfa1e681afe5afbce587bae58a9fe883bd20207c20e5bca0e4b88920207c202031e5a4a9207c2020207c2020207c0a7ce7a6bbe7babfe4bbbbe58aa1e79a84e7958ce99da2e5bc80e58f9120207c20e5bca0e4b88920207c202032e5a4a9207c2020207c2020207c0a7c20e7ae97e6b395e8b083e695b4efbc8ce58aa0e585a5e58285e9878ce58fb6e58f98e68da2e7ae97e6b395e4bc98e58c9620207c20e69d8ee59b9b20207c2033e5a4a97c202020207c202020207c2020200a7c20e5aeb9e781bee5a487e4bbbde696b9e6a188e79a84e5ae9ee696bd20207c20e78e8be4ba9420207c2034e5a4a97c202020207c202020207c2020200a7c20e69fa5e8afa2e68ea5e58fa3e79a84e695b4e79086e5928ce4bc98e58c9620207c20e8b5b5e585ad20207c2034e5a4a97c202020207c202020207c202020, '2022年5月'), ('92', null, '99', '89', null, null, '0', 0x23232320e4b8bbe8a681e789b9e680a70a0a2d20e694afe68c81e2809ce6a087e58786e2809d4d61726b646f776e202f20436f6d6d6f6e4d61726be5928c476974687562e9a38ee6a0bce79a84e8afade6b395efbc8ce4b99fe58fafe58f98e8baabe4b8bae4bba3e7a081e7bc96e8be91e599a8efbc9b0a2d20e694afe68c81e5ae9ee697b6e9a284e8a788e38081e59bbee78987efbc88e8b7a8e59f9fefbc89e4b88ae4bca0e38081e9a284e6a0bce5bc8fe69687e69cac2fe4bba3e7a0812fe8a1a8e6a0bce68f92e585a5e38081e4bba3e7a081e68a98e58fa0e38081e6909ce7b4a2e69bbfe68da2e38081e58faae8afbbe6a8a1e5bc8fe38081e887aae5ae9ae4b989e6a0b7e5bc8fe4b8bbe9a298e5928ce5a49ae8afade8a880e8afade6b395e9ab98e4baaee7ad89e58a9fe883bdefbc9b0a2d20e694afe68c81546f43efbc885461626c65206f6620436f6e74656e7473efbc89e38081456d6f6a69e8a1a8e68385e380815461736b206c69737473e3808140e993bee68ea5e7ad894d61726b646f776ee689a9e5b195e8afade6b395efbc9b0a2d20e694afe68c81546558e7a791e5ada6e585ace5bc8fefbc88e59fbae4ba8e4b61546558efbc89e38081e6b581e7a88be59bbe20466c6f77636861727420e5928c20e697b6e5ba8fe59bbe2053657175656e6365204469616772616d3b0a2d20e694afe68c81e8af86e588abe5928ce8a7a3e69e9048544d4ce6a087e7adbeefbc8ce5b9b6e4b894e694afe68c81e887aae5ae9ae4b989e8bf87e6bba4e6a087e7adbee8a7a3e69e90efbc8ce585b7e69c89e58fafe99da0e79a84e5ae89e585a8e680a7e5928ce587a0e4b98ee697a0e99990e79a84e689a9e5b195e680a7efbc9b0a2d20e694afe68c8120414d44202f20434d4420e6a8a1e59d97e58c96e58aa0e8bdbdefbc88e694afe68c8120526571756972652e6a732026616d703b205365612e6a73efbc89efbc8ce5b9b6e4b894e694afe68c81e887aae5ae9ae4b989e689a9e5b195e68f92e4bbb6efbc9b0a2d20e585bce5aeb9e4b8bbe6b581e79a84e6b58fe8a788e599a8efbc884945382befbc89e5928c5a6570746f2e6a73efbc8ce4b894e694afe68c8169506164e7ad89e5b9b3e69dbfe8aebee5a487efbc9b0a2d20e694afe68c81e887aae5ae9ae4b989e4b8bbe9a298e6a0b7e5bc8fefbc9b0a0a2320456469746f722e6d640a0a215b5d2868747470733a2f2f70616e64616f2e6769746875622e696f2f656469746f722e6d642f696d616765732f6c6f676f732f656469746f726d642d6c6f676f2d313830783138302e706e67290a0a215b5d2868747470733a2f2f696d672e736869656c64732e696f2f6769746875622f73746172732f70616e64616f2f656469746f722e6d642e7376672920215b5d2868747470733a2f2f696d672e736869656c64732e696f2f6769746875622f666f726b732f70616e64616f2f656469746f722e6d642e7376672920215b5d2868747470733a2f2f696d672e736869656c64732e696f2f6769746875622f7461672f70616e64616f2f656469746f722e6d642e7376672920215b5d2868747470733a2f2f696d672e736869656c64732e696f2f6769746875622f72656c656173652f70616e64616f2f656469746f722e6d642e7376672920215b5d2868747470733a2f2f696d672e736869656c64732e696f2f6769746875622f6973737565732f70616e64616f2f656469746f722e6d642e7376672920215b5d2868747470733a2f2f696d672e736869656c64732e696f2f626f7765722f762f656469746f722e6d642e737667290a0a2a2ae79baee5bd9520285461626c65206f6620436f6e74656e7473292a2a0a0a5b544f434d5d0a0a5b544f435d0a0a232048656164696e6720310a23232048656164696e6720320a2323232048656164696e6720330a2048656164696e6720340a232048656164696e6720350a23232048656164696e6720360a232048656164696e672031206c696e6b205b48656164696e67206c696e6b5d2868747470733a2f2f6769746875622e636f6d2f70616e64616f2f656469746f722e6d64202248656164696e67206c696e6b22290a23232048656164696e672032206c696e6b205b48656164696e67206c696e6b5d2868747470733a2f2f6769746875622e636f6d2f70616e64616f2f656469746f722e6d64202248656164696e67206c696e6b22290a2323232048656164696e672033206c696e6b205b48656164696e67206c696e6b5d2868747470733a2f2f6769746875622e636f6d2f70616e64616f2f656469746f722e6d64202248656164696e67206c696e6b22290a2048656164696e672034206c696e6b205b48656164696e67206c696e6b5d2868747470733a2f2f6769746875622e636f6d2f70616e64616f2f656469746f722e6d64202248656164696e67206c696e6b22292048656164696e67206c696e6b205b48656164696e67206c696e6b5d2868747470733a2f2f6769746875622e636f6d2f70616e64616f2f656469746f722e6d64202248656164696e67206c696e6b22290a232048656164696e672035206c696e6b205b48656164696e67206c696e6b5d2868747470733a2f2f6769746875622e636f6d2f70616e64616f2f656469746f722e6d64202248656164696e67206c696e6b22290a23232048656164696e672036206c696e6b205b48656164696e67206c696e6b5d2868747470733a2f2f6769746875622e636f6d2f70616e64616f2f656469746f722e6d64202248656164696e67206c696e6b22290a0a20e6a087e9a298efbc88e794a8e5ba95e7babfe79a84e5bda2e5bc8fefbc8948656164696e672028756e6465726c696e65290a0a5468697320697320616e2048310a3d3d3d3d3d3d3d3d3d3d3d3d3d0a0a5468697320697320616e2048320a2d2d2d2d2d2d2d2d2d2d2d2d2d0a0a23232320e5ad97e7aca6e69588e69e9ce5928ce6a8aae7babfe7ad890a202020202020202020202020202020200a2d2d2d2d0a0a7e7ee588a0e999a4e7babf7e7e203c7326e588a0e999a4e7babfefbc88e5bc80e590afe8af86e588ab48544d4ce6a087e7adbee697b6efbc893c2f73260a2ae6969ce4bd93e5ad972a2020202020205fe6969ce4bd93e5ad975f0a2a2ae7b297e4bd932a2a20205f5fe7b297e4bd935f5f0a2a2a2ae7b297e6969ce4bd932a2a2a205f5f5fe7b297e6969ce4bd935f5f5f0a0ae4b88ae6a087efbc9a583c73756226323c2f73756226efbc8ce4b88be6a087efbc9a4f3c73757026323c2f737570260a0a2a2ae7bca9e5869928e5908c48544d4ce79a8461626272e6a087e7adbe292a2a0a0a2620e58db3e69bb4e995bfe79a84e58d95e8af8de68896e79fade8afade79a84e7bca9e58699e5bda2e5bc8fefbc8ce5898de68f90e698afe5bc80e590afe8af86e588ab48544d4ce6a087e7adbee697b6efbc8ce5b7b2e9bb98e8aea4e5bc80e590af0a0a546865203c61626272207469746c653d2248797065722054657874204d61726b7570204c616e6775616765222648544d4c3c2f61626272262073706563696669636174696f6e206973206d61696e7461696e656420627920746865203c61626272207469746c653d22576f726c6420576964652057656220436f6e736f727469756d22265733433c2f61626272262e0a0a23232320e5bc95e794a820426c6f636b71756f7465730a0a2620e5bc95e794a8e69687e69cac20426c6f636b71756f7465730a0ae5bc95e794a8e79a84e8a18ce58685e6b7b7e5908820426c6f636b71756f7465730a20202020202020202020202020202020202020200a2620e5bc95e794a8efbc9ae5a682e69e9ce683b3e8a681e68f92e585a5e7a9bae799bde68da2e8a18c60e58db33c6272202f26e6a087e7adbe60efbc8ce59ca8e68f92e585a5e5a484e58588e994aee585a5e4b8a4e4b8aae4bba5e4b88ae79a84e7a9bae6a0bce784b6e5908ee59b9ee8bda6e58db3e58fafefbc8c5be699aee9809ae993bee68ea55d28687474703a2f2f6c6f63616c686f73742f29e380820a0a23232320e9949ae782b9e4b88ee993bee68ea5204c696e6b730a0a5be699aee9809ae993bee68ea55d28687474703a2f2f6c6f63616c686f73742f290a0a5be699aee9809ae993bee68ea5e5b8a6e6a087e9a2985d28687474703a2f2f6c6f63616c686f73742f2022e699aee9809ae993bee68ea5e5b8a6e6a087e9a29822290a0ae79bb4e68ea5e993bee68ea5efbc9a3c68747470733a2f2f6769746875622e636f6d260a0a5be9949ae782b9e993bee68ea55d5b616e63686f722d69645d200a0a5b616e63686f722d69645d3a20687474703a2f2f7777772e746869732d616e63686f722d6c696e6b2e636f6d2f0a0a47464d20612d7461696c206c696e6b204070616e64616f0a0a26204070616e64616f0a0a23232320e5a49ae8afade8a880e4bba3e7a081e9ab98e4baae20436f6465730a0a20e8a18ce58685e4bba3e7a08120496e6c696e6520636f64650a0ae689a7e8a18ce591bde4bba4efbc9a606e706d20696e7374616c6c206d61726b6564600a0a20e7bca9e8bf9be9a38ee6a0bc0a0ae58db3e7bca9e8bf9be59b9be4b8aae7a9bae6a0bcefbc8ce4b99fe5819ae4b8bae5ae9ee78eb0e7b1bbe4bcbc603c7072652660e9a284e6a0bce5bc8fe58c96e69687e69cac28507265666f726d6174746564205465787429e79a84e58a9fe883bde380820a0a202020203c3f7068700a20202020202020206563686f202248656c6c6f20776f726c6421223b0a202020203f260a202020200ae9a284e6a0bce5bc8fe58c96e69687e69cacefbc9a0a0a202020207c2046697273742048656164657220207c205365636f6e6420486561646572207c0a202020207c202d2d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d2d207c0a202020207c20436f6e74656e742043656c6c20207c20436f6e74656e742043656c6c20207c0a202020207c20436f6e74656e742043656c6c20207c20436f6e74656e742043656c6c20207c0a0a204a53e4bba3e7a081e380800a0a6060606a6176617363726970740a66756e6374696f6e207465737428297b0a09636f6e736f6c652e6c6f67282248656c6c6f20776f726c642122293b0a7d0a200a2866756e6374696f6e28297b0a2020202076617220626f78203d2066756e6374696f6e28297b0a202020202020202072657475726e20626f782e666e2e696e697428293b0a202020207d3b0a0a20202020626f782e70726f746f74797065203d20626f782e666e203d207b0a2020202020202020696e6974203a2066756e6374696f6e28297b0a202020202020202020202020636f6e736f6c652e6c6f672827626f782e696e6974282927293b0a0a09090972657475726e20746869733b0a20202020202020207d2c0a0a0909616464203a2066756e6374696f6e28737472297b0a090909616c6572742822616464222c20737472293b0a0a09090972657475726e20746869733b0a09097d2c0a0a090972656d6f7665203a2066756e6374696f6e28737472297b0a090909616c657274282272656d6f7665222c20737472293b0a0a09090972657475726e20746869733b0a09097d0a202020207d3b0a202020200a20202020626f782e666e2e696e69742e70726f746f74797065203d20626f782e666e3b0a202020200a2020202077696e646f772e626f78203d626f783b0a7d2928293b0a0a7661722074657374426f78203d20626f7828293b0a74657374426f782e61646428226a517565727922292e72656d6f766528226a517565727922293b0a6060600a0a2048544d4ce4bba3e7a0812048544d4c20636f6465730a0a60606068746d6c0a3c21444f43545950452068746d6c260a3c68746d6c260a202020203c68656164260a20202020202020203c6d61746520636861726573743d227574662d3822202f260a20202020202020203c7469746c652648656c6c6f20776f726c64213c2f7469746c65260a202020203c2f68656164260a202020203c626f6479260a20202020202020203c68312648656c6c6f20776f726c64213c2f6831260a202020203c2f626f6479260a3c2f68746d6c260a6060600a0a23232320e59bbee7898720496d616765730a0a496d6167653a0a0a215b5d2868747470733a2f2f70616e64616f2e6769746875622e696f2f656469746f722e6d642f6578616d706c65732f696d616765732f342e6a7067290a0a2620466f6c6c6f7720796f75722068656172742e0a0a215b5d2868747470733a2f2f70616e64616f2e6769746875622e696f2f656469746f722e6d642f6578616d706c65732f696d616765732f382e6a7067290a0a2620e59bbee4b8baefbc9ae58ea6e997a8e799bde59f8ee6b299e6bba90a0ae59bbee78987e58aa0e993bee68ea52028496d616765202b204c696e6b29efbc9a0a0a5b215b5d2868747470733a2f2f70616e64616f2e6769746875622e696f2f656469746f722e6d642f6578616d706c65732f696d616765732f372e6a7067295d2868747470733a2f2f70616e64616f2e6769746875622e696f2f656469746f722e6d642f6578616d706c65732f696d616765732f372e6a70672022e69d8ee581a5e9a696e5bca0e4b893e8be91e3808ae4bcbce6b0b4e6b581e5b9b4e3808be5b081e99da222290a0a2620e59bbee4b8baefbc9ae69d8ee581a5e9a696e5bca0e4b893e8be91e3808ae4bcbce6b0b4e6b581e5b9b4e3808be5b081e99da20a202020202020202020202020202020200a2d2d2d2d0a0a23232320e58897e8a1a8204c697374730a0a20e697a0e5ba8fe58897e8a1a8efbc88e5878fe58fb7efbc89556e6f726465726564204c6973747320282d290a202020202020202020202020202020200a2d20e58897e8a1a8e4b8800a2d20e58897e8a1a8e4ba8c0a2d20e58897e8a1a8e4b8890a20202020200a20e697a0e5ba8fe58897e8a1a8efbc88e6989fe58fb7efbc89556e6f726465726564204c6973747320282a290a0a2a20e58897e8a1a8e4b8800a2a20e58897e8a1a8e4ba8c0a2a20e58897e8a1a8e4b8890a0a20e697a0e5ba8fe58897e8a1a8efbc88e58aa0e58fb7e5928ce5b58ce5a597efbc89556e6f726465726564204c6973747320282b290a202020202020202020202020202020200a2b20e58897e8a1a8e4b8800a2b20e58897e8a1a8e4ba8c0a202020202b20e58897e8a1a8e4ba8c2d310a202020202b20e58897e8a1a8e4ba8c2d320a202020202b20e58897e8a1a8e4ba8c2d330a2b20e58897e8a1a8e4b8890a202020202a20e58897e8a1a8e4b8800a202020202a20e58897e8a1a8e4ba8c0a202020202a20e58897e8a1a8e4b8890a0a20e69c89e5ba8fe58897e8a1a8204f726465726564204c6973747320282d290a202020202020202020202020202020200a312e20e7acace4b880e8a18c0a322e20e7acace4ba8ce8a18c0a332e20e7acace4b889e8a18c0a0a2047464d207461736b206c6973740a0a2d205b785d2047464d207461736b206c69737420310a2d205b785d2047464d207461736b206c69737420320a2d205b205d2047464d207461736b206c69737420330a202020202d205b205d2047464d207461736b206c69737420332d310a202020202d205b205d2047464d207461736b206c69737420332d320a202020202d205b205d2047464d207461736b206c69737420332d330a2d205b205d2047464d207461736b206c69737420340a202020202d205b205d2047464d207461736b206c69737420342d310a202020202d205b205d2047464d207461736b206c69737420342d320a202020202020202020202020202020200a2d2d2d2d0a20202020202020202020202020202020202020200a23232320e7bb98e588b6e8a1a8e6a0bc205461626c65730a0a7c20e9a1b9e79bae20202020202020207c20e4bbb7e6a0bc2020207c2020e695b0e9878f20207c0a7c202d2d2d2d2d2d2d2d2020207c202d2d2d2d2d3a20207c203a2d2d2d2d3a20207c0a7c20e8aea1e7ae97e69cba2020202020207c2024313630302020207c2020203520202020207c0a7c20e6898be69cba20202020202020207c2020202431322020207c20202031322020207c0a7c20e7aea1e7babf20202020202020207c202020202431202020207c202032333420207c0a20202020202020202020202020202020202020200a46697273742048656164657220207c205365636f6e64204865616465720a2d2d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d2d0a436f6e74656e742043656c6c20207c20436f6e74656e742043656c6c0a436f6e74656e742043656c6c20207c20436f6e74656e742043656c6c200a0a7c2046697273742048656164657220207c205365636f6e6420486561646572207c0a7c202d2d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d2d207c0a7c20436f6e74656e742043656c6c20207c20436f6e74656e742043656c6c20207c0a7c20436f6e74656e742043656c6c20207c20436f6e74656e742043656c6c20207c0a0a7c2046756e6374696f6e206e616d65207c204465736372697074696f6e20202020202020202020202020202020202020207c0a7c202d2d2d2d2d2d2d2d2d2d2d2d2d207c202d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d207c0a7c206068656c702829602020202020207c20446973706c6179207468652068656c702077696e646f772e202020202020207c0a7c206064657374726f792829602020207c202a2a44657374726f7920796f757220636f6d7075746572212a2a20202020207c0a0a7c204c6566742d416c69676e656420207c2043656e74657220416c69676e656420207c20526967687420416c69676e6564207c0a7c203a2d2d2d2d2d2d2d2d2d2d2d2d207c3a2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d3a7c202d2d2d2d2d3a7c0a7c20636f6c20332069732020202020207c20736f6d6520776f7264792074657874207c202431363030207c0a7c20636f6c20322069732020202020207c2063656e746572656420202020202020207c202020243132207c0a7c207a656272612073747269706573207c20617265206e65617420202020202020207c202020202431207c0a0a7c204974656d2020202020207c2056616c7565207c0a7c202d2d2d2d2d2d2d2d2d207c202d2d2d2d2d3a7c0a7c20436f6d707574657220207c202431363030207c0a7c2050686f6e6520202020207c202020243132207c0a7c20506970652020202020207c202020202431207c0a202020202020202020202020202020200a2d2d2d2d0a0a20e789b9e6ae8ae7aca6e58fb72048544d4c20456e74697469657320436f6465730a0a26616d703b636f70793b2026616d703b202026616d703b756d6c3b2026616d703b74726164653b2026616d703b696578636c3b2026616d703b706f756e643b0a26616d703b616d703b2026616d703b6c743b2026616d703b67743b2026616d703b79656e3b2026616d703b6575726f3b2026616d703b7265673b2026616d703b706c75736d6e3b2026616d703b706172613b2026616d703b736563743b2026616d703b6272766261723b2026616d703b6d6163723b2026616d703b6c6171756f3b2026616d703b6d6964646f743b200a0a5826616d703b737570323b205926616d703b737570333b2026616d703b6672616333343b2026616d703b6672616331343b202026616d703b74696d65733b202026616d703b6469766964653b20202026616d703b726171756f3b0a0a313826616d703b6f72646d3b43202026616d703b71756f743b202026616d703b61706f733b0a0a0a20e58f8de6969ce69da0204573636170650a0a5c2a6c69746572616c20617374657269736b735c2a0a2020202020202020202020200a23232320e7a791e5ada6e585ace5bc8f20546558284b61546558290a20202020202020202020202020202020202020200a2424453d6d635e3224240a0ae8a18ce58685e79a84e585ace5bc8f2424453d6d635e322424e8a18ce58685e79a84e585ace5bc8fefbc8ce8a18ce58685e79a842424453d6d635e322424e585ace5bc8fe380820a0a24245c285c737172747b33782d317d2b28312b78295e325c2924240a20202020202020202020202020202020202020200a24245c73696e285c616c706861295e7b5c74686574617d3d5c73756d5f7b693d307d5e7b6e7d28785e69202b205c636f732866292924240a0ae5a49ae8a18ce585ace5bc8fefbc9a0a0a6060606d6174680a5c646973706c61797374796c650a5c6c65667428205c73756d5c5f7b6b3d317d5e6e20615c5f6b20625c5f6b205c7269676874295e320a5c6c65710a5c6c65667428205c73756d5c5f7b6b3d317d5e6e20615c5f6b5e32205c7269676874290a5c6c65667428205c73756d5c5f7b6b3d317d5e6e20625c5f6b5e32205c7269676874290a6060600a0a6060606b617465780a5c646973706c61797374796c65200a202020205c667261637b317d7b0a20202020202020205c4269676c285c737172747b5c706869205c737172747b357d7d2d5c7068695c426967722920655e7b0a20202020202020205c667261633235205c70697d7d203d20312b5c667261637b655e7b2d325c70697d7d207b312b5c667261637b655e7b2d345c70697d7d207b0a2020202020202020312b5c667261637b655e7b2d365c70697d7d0a20202020202020207b312b5c667261637b655e7b2d385c70697d7d0a2020202020202020207b312b5c63646f74737d207d0a20202020202020207d200a202020207d0a6060600a0a6060606c617465780a66287829203d205c696e745f7b2d5c696e6674797d5e5c696e6674790a202020205c6861742066285c7869295c2c655e7b32205c70692069205c786920787d0a202020205c2c645c78690a6060600a0a23232320e7bb98e588b6e5ba8fe58897e59bbe2053657175656e6365204469616772616d0a20202020202020202020202020202020202020200a6060607365710a416e647265772d264368696e613a20536179732048656c6c6f200a4e6f7465207269676874206f66204368696e613a204368696e61207468696e6b735c6e61626f7574206974200a4368696e612d2d26416e647265773a20486f772061726520796f753f200a416e647265772d26264368696e613a204920616d20676f6f64207468616e6b73210a6060600a0a23232320456e64, 'markdown教程'), ('93', null, '99', '86', null, null, '0', 0x23232320e78988e69cace58fb7e5b88ce69c9be981b5e5beaae5a4a7e4b8ade5b08fe78988e69cace58fb7e7bc96e588b6efbc9a0a0a0a205be5bbbae8aeae5d582e30e4b8bae5a4a7e78988e69cace694b9e78988e4b88ae7babfefbc9b582e582e30e4b8bae5b08fe78988e69cace4bc98e58c96e58d87e7baa7efbc9b582e582e58e4b8bae5908ce78988e69cace79a84627567e4bfaee5a48de69bb4e696b0e380820a0ae6af94e5a682efbc9a626c6f67322e3020e4b8bae58d9ae5aea2e7b3bbe7bb9fe5a4a7e694b9e7898856322e30e79a84e8b1a1e5be81efbc9b626c6f67322e3120e4b8bae58d9ae5aea2e7b3bbe7bb9fe5b08fe78988e69cace58d87e7baa756322e31e79a84e8b1a1e5be81efbc9b626c6f67322e312e31e4b8bae58d9ae5aea2e7b3bbe7bb9fe5908ce78988e69cac627567e4bfaee5a48de4bc98e58c96e79a84e4bfa1e58fb7e38082, '版本控制规范'), ('95', null, '99', null, '94', '2018-12-05 14:40:59', '0', null, 'tt'), ('97', null, '99', null, '96', '2018-12-05 14:41:28', '0', null, 'aaa');
COMMIT;

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', b'1', '2018-12-05 16:08:26', b'0', null, '8a6251d6b8256c4ab0f926a268281f91', '22cea8ada827035234c3e28dc8c4d7cd', 'shimh', '0'), ('98', b'0', '2018-12-05 16:23:15', null, null, 'cee565fe91efeaf41478ef72ea104ff7', '184ccb38360f69f50abb67b1c066706d', 'shimh2', '1'), ('105', b'0', '2018-12-05 16:40:32', null, null, '049839ad155161315b221c34c58d7568', 'fcc1fc3ab98f213f5d3c9af940b6a40f', 'shimh2', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
