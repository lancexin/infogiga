package cn.infogiga.exp.filther;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyCharacterEncodingFilter extends org.springframework.web.filter.CharacterEncodingFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//System.out.println("进入filther");
		super.doFilterInternal(request, response, filterChain);
	}

}
