package com.api.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/*
 *  处理 文件上传的 servlet 
 * 	
 * 
 * 		导包 :   commons-fileupload-1.2.1.jar
 		commons-io-1.4.jar
 * 
 */
public class UploadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("---UploadServlet2---");

		// 弄一个数组, 里面写上 允许的 文件的 扩展名
		String[] extensions = { ".png", ".bmp", ".gif", ".jpg", ".avi",
				".rmvb", ".doc", ".txt" };

		// 判断是否是 文件上传的 请求, 如果不是 , 那么就给用户一个 友好的提示
		if (!ServletFileUpload.isMultipartContent(request)) {

			// 如果进来, 则说明不是文件上传的 请求, 那么就给用户提示, 请先设置 表单 相关的属性 之后 再 提交请求
			request.setAttribute("message", "对不起, 不是文件上传的表单, 请检查 相关的属性 设置...");

			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		}

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 设置 临时缓冲区 所在的文件夹
			// 一般开发过程中, 上传的文件是保存在 当前的 web工程下的 .

			// 临时 缓冲区 产生的缓存 文件 的位置
			factory.setRepository(new File(getServletContext().getRealPath(
					"/temp")));
			factory.setSizeThreshold(1024 * 1024); // 设置临时缓冲区的大小 是 1M

			// 拿到 解析器 对象
			ServletFileUpload parser = new ServletFileUpload(factory);
			parser.setHeaderEncoding("UTF-8");

			// 设置单个的上传的文件的大小
			parser.setFileSizeMax(1024 * 1024 * 2); // 单个 文件不能超过 2M

			// 设置总 的上传的文件的大小
			parser.setSizeMax(1024 * 1024 * 20); // 总文件 大小 文件不能超过 20 M

			// 设置 一个 进度 监听器, 设置了之后, 解析器 就会自动去调用 监听器的 方法 update
			parser.setProgressListener(new ProgressListener() {

				// pBytesRead ---当前 读到的 数据 是 多少了
				// pContentLength ---当前 解析到的fileItem的长度是多少
				// pItems ---当前 解析到第几个item了

				private long megaBytes = -1;

				public void update(long pBytesRead, long pContentLength,int pItems) {
					
//					第一次 :　　４０００００　－－－－－－－　０　　－－－０　　　　　megaBytes　　　０
//					第二次 :　　８０００００　－－－－－－－　０　　－－－０　　　　　
//					第二次 :　１１０００００　－－－－－－－　１　　－－－１　　　　　megaBytes　　　１　
//					第二次 :　１９０００００　－－－－－－－　１　　－－－１　　　　　megaBytes　　　１　
//					第二次 :　２２０００００　－－－－－－－　２　　－－－２　　　　　megaBytes　　　２　
					
					long mBytes = pBytesRead / 1000000;
					if (megaBytes == mBytes) {
						return;
					}
					megaBytes = mBytes;
					System.out.println("We are currently reading item "
							+ pItems);
					if (pContentLength == -1) {
						System.out.println("So far, " + pBytesRead
								+ " bytes have been read.");
					} else {
						System.out.println("So far, " + pBytesRead + " of "
								+ pContentLength + " bytes have been read.");
					}
				}
			});

			List<FileItem> list = parser.parseRequest(request);
			
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					// 说明是普通输入项
					// 获得提交过来的参数的名称
					String fieldName = fileItem.getFieldName();
					String value = fileItem.getString("UTF-8");

					System.out.println(fieldName + " = " + value);
				} else {
					// 说明是 文件上传 输入项
					// 获得文件的名称

					// C:\Users\Administrator\Desktop\aaa.txt
					// aaa.txt
					String filename = fileItem.getName();

					// 要去特殊 处理
					int lastIndexOf = filename.lastIndexOf("\\");

					if (lastIndexOf != -1) {

						// 说明 是 C:\Users\Administrator\Desktop\aaa.txt

						// aaa.txt
						filename = filename.substring(lastIndexOf + 1);
					}

					// aaa.txt
					int index = filename.lastIndexOf(".");

					// 拿到 上传的文件的扩展名
					String fileExt = filename.substring(index);

					String exts = Arrays.toString(extensions);

					if (!exts.contains(fileExt)) {

						// 友好提示, 对不起, 你上传的文件 不在 运行的范围内
						request.setAttribute("message", "您上传的   " + fileExt
								+ "类型是 不符合  规范的 ...");

						request.getRequestDispatcher("/message.jsp").forward(
								request, response);

					}

					// 定义 一个输出流
					// File file = new File(getServletContext().getRealPath(
					// "/WEB-INF/upload"));
					// // f:\\aa\bb..\\upload\aa.txt
					// //
					// f:\\aa\bb..\\upload\C:\Users\Administrator\Desktop\aaa.txt

					String realPath = getServletContext().getRealPath(
							"/WEB-INF/upload");

					// 生成随机 文件夹
					String savePath = generateSavePath(realPath, filename);

					String uuidname = generateUUIDName(filename);

					// 获得文件的输入 流
					InputStream in = fileItem.getInputStream();

					// 将上传 的文件写到 生成 的随机文件夹 中保存起来
					OutputStream out = new FileOutputStream(new File(savePath,
							uuidname));

					// io 流对拷
					byte[] buf = new byte[1024];
					int len = 0;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					// 关闭流
					in.close();
					out.close();

					// 将上传时 产生的临时文件 删掉 ...
					fileItem.delete();
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {

			request.setAttribute("message", "对不起, 单个文件大小不能超过 	2M ...");

			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		} catch (FileUploadBase.SizeLimitExceededException e) {

			request.setAttribute("message", "对不起,  总  文件大小不能超过 	20 M ...");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("上传成功....");

		
		response.setHeader("content-disposition", "attachement;filename=aa.txt");
		
		
		//还需要将  要下载的 文件 当作 一个 inputStream 流 读进来, 
		// 读进来 再 写到   response.getOutputStream中去就可以了. 
		
	}

	// 生成全球唯一的 名字
	private String generateUUIDName(String filename) {

		// 04e78bd1-3c94-41d5-ad36-01f2eab33801_aaa.txt
		return UUID.randomUUID().toString() + "_" + filename;

	}

	// 生成 随机的文件夹
	private String generateSavePath(String realPath, String filename) {
		int hashCode = filename.hashCode();

		int dir1 = hashCode & 0xf; // 一级 目录 1
		int dir2 = (hashCode >> 4) & 0xf; // 二级目录 4

		// WEB-INF/upload/1/4/xx.txt
		// d://aa//bb//cc/upload/1/4
		String savePath = realPath + "/" + dir1 + "/" + dir2;

		File file = new File(savePath);

		if (!file.exists()) {

			file.mkdirs();
		}

		return savePath;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
