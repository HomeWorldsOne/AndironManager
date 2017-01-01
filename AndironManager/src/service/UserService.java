package service;

import java.sql.SQLException;
import java.util.UUID;
import dao.UserDAO;
import dto.User;
import exception.*;


public class UserService {

	/**
	 * Coordinator login function
	 * @param email
	 * @param password
	 * @throws NoSuchEmailException
	 * @throws InvalidPasswordException 
	 * @throws AccountLockedException 
	 * @ 
	 */
	public static User login(String email, String password) 
			throws NoSuchEmailException, InvalidPasswordException, AccountLockedException {
		User user = UserDAO.selectByEmail(email);
		
		if(user == null) {
			throw new NoSuchEmailException();
		}  else if(user.getUserLocked() == 1) {
			System.out.println("USER ACCOUNT LOCKED");
			throw new AccountLockedException();
		} else if(!user.getUserPassword().equals(password)) {
			
			int failed = user.getUserLoginAttempt();
			failed += 1;
			user.setUserLoginAttempt(failed);

			System.out.println("Login Attempt: "+user.getUserLoginAttempt());
			
			UserDAO.addOrUpdate(user);

			if(failed > 3) {
				lockUser(user);
				throw new AccountLockedException();
			} else {
				throw new InvalidPasswordException();
			}
		}
		return user;
	}
	
	public static void register(User user) throws DuplicateEmailException {
		User exist = UserDAO.selectByEmail(user.getUserEmail());
		if(exist != null) {
			throw new DuplicateEmailException();
		}
		
		// Otherwise, add
		UserDAO.add(user);
	}
	
	public static int getLoginAttempt(User user) {
		User usr = UserDAO.selectById(user.getUserId());
		return usr.getUserLoginAttempt();
	}
	
	public static void lockUser(User user) {
		user.setUserLocked(1);
		UserDAO.addOrUpdate(user);
		
		// Generate temporary password
		String temporary = UUID.randomUUID().toString();
		user.setUserPassword(temporary);
		
		// Generate unlockCode
		String unlockCode = UUID.randomUUID().toString();
		user.setUserUnlockCode(unlockCode);
		
		UserDAO.update(user);
		
		// Send verification email
		//NotificationEmail.sendVerificationEmail(user);
	}
	
	public static void unlockUser(User user) {
		user.setUserLocked(0);
		user.setUserUnlockCode(null);
		user.setUserLoginAttempt(0);
		UserDAO.addOrUpdate(user);
	}
	
	public static void resetPassword(User user) {
		// Generate temporary password
		String temporary = UUID.randomUUID().toString();
		user.setUserPassword(temporary);
		
		UserDAO.update(user);
		
		// send email with temporary password
		//NotificationEmail.sendRecoveryEmail(user);
	}
	
}

