package org.irods.jargon.ticket.packinstr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.packinstr.KeyValuePair;

/**
 * Packing instruction for admin functions for the ticket subsystem in iRODS.
 * These functions mirror the packing instructions in the iticket icommand.
 *
 * @author Mike Conway - DICE (www.irods.org)
 *
 */
public class TicketAdminInp extends TicketInp {
	
	public static final String ADMIN_KW = "irodsAdmin";

	public static TicketAdminInp instance() {
		return new TicketAdminInp();
	}

	public static TicketAdminInp instanceForDelete(final String ticketId) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "delete", ticketId, BLANK, BLANK, BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForDeleteWithAdminPrivileges(final String ticketId) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}
		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "delete", ticketId, BLANK, BLANK, BLANK, BLANK, condInput);
	}

	public static TicketAdminInp instanceForCreate(final TicketCreateModeEnum mode, final String fullPath,
			final String ticketId) {
		if (mode == null) {
			throw new IllegalArgumentException("null permission mode");
		}
		if (fullPath == null || (fullPath.isEmpty())) {
			throw new IllegalArgumentException("null or empty full path name");
		}
		// ticketId is not optional
		if ((ticketId == null) || (ticketId.isEmpty())) {
			throw new IllegalArgumentException("null or empty full path name");
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "create", ticketId, mode.getTextValue(), fullPath, BLANK,
				BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForCreateWithAdminPrivileges(final TicketCreateModeEnum mode, final String fullPath,
			final String ticketId) throws JargonException {
		if (mode == null) {
			throw new IllegalArgumentException("null permission mode");
		}
		if (fullPath == null || (fullPath.isEmpty())) {
			throw new IllegalArgumentException("null or empty full path name");
		}
		// ticketId is not optional
		if ((ticketId == null) || (ticketId.isEmpty())) {
			throw new IllegalArgumentException("null or empty full path name");
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "create", ticketId, mode.getTextValue(), fullPath, BLANK,
				BLANK, condInput);
	}

	// TODO: create another method for create with no ticketId param? public
	// static TicketAdminInp instanceForCreate(final String mode, String
	// fullPath)

	public static TicketAdminInp instanceForList() throws JargonException {
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "list", BLANK, BLANK, BLANK, BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForListWithPrivileges() throws JargonException {
		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "list", BLANK, BLANK, BLANK, BLANK, BLANK, condInput);
	}

	public static TicketAdminInp instanceForList(final String ticketId) {
		String id = BLANK;

		// ticketId is optional??
		if ((ticketId != null) && (!ticketId.isEmpty())) {
			id = ticketId;
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "list", id, BLANK, BLANK, BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForListWithPrivileges(final String ticketId) throws JargonException {
		String id = BLANK;

		// ticketId is optional??
		if ((ticketId != null) && (!ticketId.isEmpty())) {
			id = ticketId;
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "list", id, BLANK, BLANK, BLANK, BLANK, condInput);
	}

	// TODO: create another method for list with no param? public static
	// TicketAdminInp instanceForList()

	public static TicketAdminInp instanceForListAll() {
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "list-all", BLANK, BLANK, BLANK, BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForListAllWithAdminPrivileges() throws JargonException {
		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "list-all", BLANK, BLANK, BLANK, BLANK, BLANK, condInput);
	}

	public static TicketAdminInp instanceForModifyAddAccess(final String ticketId,
			final TicketModifyAddOrRemoveTypeEnum addTypeEnum, final String modObject) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}
		// check and see if add type is set
		if (addTypeEnum == null) {
			throw new IllegalArgumentException("null modify add permission type not set");
		}
		// check and see if action is set
		if (modObject == null || modObject.isEmpty()) {
			throw new IllegalArgumentException("null or empty modify add - user, group, or host");
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "add", addTypeEnum.getTextValue(),
				modObject, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForModifyAddAccessWithAdminPrivileges(final String ticketId,
			final TicketModifyAddOrRemoveTypeEnum addTypeEnum, final String modObject) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}
		// check and see if add type is set
		if (addTypeEnum == null) {
			throw new IllegalArgumentException("null modify add permission type not set");
		}
		// check and see if action is set
		if (modObject == null || modObject.isEmpty()) {
			throw new IllegalArgumentException("null or empty modify add - user, group, or host");
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "add", addTypeEnum.getTextValue(),
				modObject, BLANK, condInput);
	}

	public static TicketAdminInp instanceForModifyRemoveAccess(final String ticketId,
			final TicketModifyAddOrRemoveTypeEnum addTypeEnum, final String modObject) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}
		// check and see if add type is set
		if (addTypeEnum == null) {
			throw new IllegalArgumentException("null modify remove permission type not set");
		}
		// check and see if action is set
		if (modObject == null || modObject.isEmpty()) {
			throw new IllegalArgumentException("null or empty modify remove - user, group, or host");
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "remove", addTypeEnum.getTextValue(),
				modObject, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForModifyRemoveAccessWithAdminPrivileges(final String ticketId,
			final TicketModifyAddOrRemoveTypeEnum addTypeEnum, final String modObject) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}
		// check and see if add type is set
		if (addTypeEnum == null) {
			throw new IllegalArgumentException("null modify remove permission type not set");
		}
		// check and see if action is set
		if (modObject == null || modObject.isEmpty()) {
			throw new IllegalArgumentException("null or empty modify remove - user, group, or host");
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "remove", addTypeEnum.getTextValue(),
				modObject, BLANK, condInput);
	}

	public static TicketAdminInp instanceForModifyNumberOfUses(final String ticketId, final Integer numberOfUses) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (numberOfUses < 0) {
			throw new IllegalArgumentException("illegal integer for uses - must be 0 or greater");
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "uses", numberOfUses.toString(), BLANK,
				BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForModifyNumberOfUsesWithAdminPrivileges(final String ticketId, final Integer numberOfUses) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (numberOfUses < 0) {
			throw new IllegalArgumentException("illegal integer for uses - must be 0 or greater");
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "uses", numberOfUses.toString(), BLANK,
				BLANK, condInput);
	}

	public static TicketAdminInp instanceForModifyFileWriteNumber(final String ticketId,
			final Integer numberOfFileWrites) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (numberOfFileWrites < 0) {
			throw new IllegalArgumentException("illegal integer for write-file - must be 0 or greater");
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "write-file",
				numberOfFileWrites.toString(), BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForModifyFileWriteNumberWithAdminPrivileges(final String ticketId,
			final Integer numberOfFileWrites) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (numberOfFileWrites < 0) {
			throw new IllegalArgumentException("illegal integer for write-file - must be 0 or greater");
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "write-file",
				numberOfFileWrites.toString(), BLANK, BLANK, condInput);
	}

	public static TicketAdminInp instanceForModifyByteWriteNumber(final String ticketId, final long byteWriteLimit) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (byteWriteLimit < 0) {
			throw new IllegalArgumentException("illegal integer for write-byte - must be 0 or greater");
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "write-bytes",
				String.valueOf(byteWriteLimit), BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForModifyByteWriteNumberWithAdminPrivileges(final String ticketId, final long byteWriteLimit) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (byteWriteLimit < 0) {
			throw new IllegalArgumentException("illegal integer for write-byte - must be 0 or greater");
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "write-bytes",
				String.valueOf(byteWriteLimit), BLANK, BLANK, condInput);
	}

	public static TicketAdminInp instanceForModifyExpiration(final String ticketId, final String expirationDate) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (expirationDate == null || expirationDate.isEmpty()) {
			throw new IllegalArgumentException("null or empty expiration date");
		}

		// check date format
		if (!MODIFY_DATE_FORMAT.matcher(expirationDate).matches()) {
			throw new IllegalArgumentException("illegal expiration date");
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "expire", expirationDate, BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForModifyExpirationWithAdminPrivileges(final String ticketId, final String expirationDate) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		if (expirationDate == null || expirationDate.isEmpty()) {
			throw new IllegalArgumentException("null or empty expiration date");
		}

		// check date format
		if (!MODIFY_DATE_FORMAT.matcher(expirationDate).matches()) {
			throw new IllegalArgumentException("illegal expiration date");
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "expire", expirationDate, BLANK, BLANK, condInput);
	}

	/**
	 * Create a packing instruction to modify the expiration date. Setting the date
	 * to {@code null} removes the expiration
	 *
	 * @param ticketId       {@code String} with the unique ticket string
	 * @param expirationDate {@code Date} or {@code null} to remove
	 * @return {@link TicketAdminInp}
	 */
	public static TicketAdminInp instanceForModifyExpiration(final String ticketId, final Date expirationDate) {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		String formattedDate = "";

		if (expirationDate != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
			formattedDate = df.format(expirationDate);
		}

		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "expire", formattedDate, BLANK, BLANK, new ArrayList<>());
	}

	public static TicketAdminInp instanceForModifyExpirationWithAdminPrivileges(final String ticketId, final Date expirationDate) throws JargonException {
		if (ticketId == null || ticketId.isEmpty()) {
			throw new IllegalArgumentException("null or empty ticket id");
		}

		String formattedDate = "";

		if (expirationDate != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
			formattedDate = df.format(expirationDate);
		}

		List<KeyValuePair> condInput = new ArrayList<>();
		condInput.add(KeyValuePair.instance(ADMIN_KW, ""));
		return new TicketAdminInp(TICKET_ADMIN_INP_API_NBR, "mod", ticketId, "expire", formattedDate, BLANK, BLANK, condInput);
	}
	
	public void setArg1(String value) {
		arg1 = value;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg2(String value) {
		arg2 = value;
	}

	public String getArg2() {
		return arg2;
	}

	public void setArg3(String value) {
		arg3 = value;
	}

	public String getArg3() {
		return arg3;
	}

	public void setArg4(String value) {
		arg4 = value;
	}

	public String getArg4() {
		return arg4;
	}

	public void setArg5(String value) {
		arg5 = value;
	}

	public String getArg5() {
		return arg5;
	}

	public void setArg6(String value) {
		arg6 = value;
	}

	public String getArg6() {
		return arg6;
	}

	private TicketAdminInp() {
		super();
	}

	/**
	 * Private constructor for TicketAdminInp, use the instance() methods to create
	 * per command
	 *
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @param arg5
	 * @param arg6
	 * @param condInput
	 */
	private TicketAdminInp(final int apiNbr, final String arg1, final String arg2, final String arg3, final String arg4,
			final String arg5, final String arg6, final List<KeyValuePair> condInput) {
		super(apiNbr, arg1, arg2, arg3, arg4, arg5, arg6, condInput);
	}

}
